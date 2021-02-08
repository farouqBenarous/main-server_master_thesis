package com.logic;

import com.entity.entities.Almond;
import com.entity.entities.AlmondClassificationResponse;
import com.entity.entities.AlmondProcessingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AlmondService {

    AlmondProcessingResult process(MultipartFile image) throws IOException;

    AlmondClassificationResponse classify(AlmondProcessingResult processedImage,Integer limit) throws IOException;

    List<Almond> getAlmonds();

    Boolean deleteAllAlmonds();

    Boolean initDataTable(List<Almond> almonds);

}
