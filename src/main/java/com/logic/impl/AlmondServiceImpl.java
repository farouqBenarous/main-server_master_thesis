package com.logic.impl;

import com.database.delegator.AlmondsDelegator;
import com.entity.entities.*;
import com.logic.AlmondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static com.utils.Constants.*;
import static com.utils.Utils.constructRequestClassifier;
import static com.utils.Utils.constructRequestImage;


@Service
public class AlmondServiceImpl implements AlmondService {

    @Autowired
    AlmondsDelegator almondsDelegator;

    private final RestTemplate restTemplate;

    public static final String ENTITY_NAME = "Almond";

    public AlmondServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public AlmondProcessingResult process(MultipartFile image) throws IOException {
        String url = ALMOND_IMAGE_PROCESSOR_SERVER_ADDRESS + ALMOND_IMAGE_PROCESSOR_ENDPOINT;
        HttpEntity<MultiValueMap<String, Object>> requestEntity = constructRequestImage(image);

        ResponseEntity<AlmondProcessingResult> response = this.restTemplate.postForEntity(url, requestEntity, AlmondProcessingResult.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    @Override
    public AlmondClassificationResponse classify(AlmondProcessingResult processedImage, Integer limit) throws IOException {
        String url = ALMOND_CLASSIFIER_SERVER_ADDRESS + ALMOND_CLASSIFIER_ENDPOINT;

        HttpEntity<MultiValueMap<String, Object>> requestEntity = constructRequestClassifier(processedImage.getImageNumpy());
        ResponseEntity<AlmondClassificationResult> result = this.restTemplate.postForEntity(url, requestEntity, AlmondClassificationResult.class);

        if (result.getStatusCode() == HttpStatus.OK) {
            AlmondClassificationResponse response = new AlmondClassificationResponse();
            List<Double> convertedResult = (List<Double>) result.getBody().getPredictions().get(0);

            convertedResult.forEach(pr -> {
                ClassificationResponse classificationResponse = new ClassificationResponse();
                Almond almond = almondsDelegator.getAlmondByIndex(String.valueOf(convertedResult.indexOf(pr)));
                classificationResponse.setEntity(ENTITY_NAME);
                classificationResponse.setScore(pr.toString());
                classificationResponse.setEntityClass(almond);
                response.addPrediction(classificationResponse);
            });


            //sort
            Collections.sort(response.getPredictions(), new Comparator<ClassificationResponse>() {
                @Override
                public int compare(ClassificationResponse u1, ClassificationResponse u2) {
                    return Double.valueOf(u2.getScore()).compareTo(Double.valueOf(u1.getScore()));
                }
            });

            //to percentage
            response.getPredictions().forEach(pr -> {
                pr.setScore(ClassificationResponse.toPercentage(Double.valueOf(pr.getScore())));
            });

            //limit
            if (limit != null) {
                response.setPredictions(response.getPredictions().subList(0, limit));
            }
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<Almond> getAlmonds() {
        return almondsDelegator.getAlmonds();
    }


    @Override
    public Boolean deleteAllAlmonds() {
        return almondsDelegator.deleteAllAlmonds();
    }

    @Override
    public Boolean initDataTable(List<Almond> almonds) {
        return almondsDelegator.initDataTable(almonds);
    }
}
