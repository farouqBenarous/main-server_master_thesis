package com.utils;

import com.entity.entities.AlmondClassificationRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class Utils {

    public static Gson gson = new Gson().newBuilder().create();

    /**
     * this function construct the request for the image processor API
     */
    public static HttpEntity<MultiValueMap<String, Object>> constructRequestImage(MultipartFile image) throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        //body.add("image", image);
        body.add("image", readAndWriteFile(image));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        return requestEntity;
    }

    /**
     * this function takes a MultipartFile as an argument it writes it to a local file and then  read and return that same file
     * solves the image serilisation issue
     */
    public static FileSystemResource readAndWriteFile(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));

        stream.write(bytes);
        stream.close();

        return new FileSystemResource(file.getOriginalFilename());
    }

    /**
     * this function construct the request for the Tensorflow serving API
     */
    public static HttpEntity<MultiValueMap<String, Object>> constructRequestClassifier(String jsonArray) throws IOException {
        Type listType = new TypeToken<List<Object>>(){}.getType();

        AlmondClassificationRequest body = new AlmondClassificationRequest(gson.fromJson(jsonArray,listType));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity(gson.toJson(body), headers);
        return requestEntity;
    }

    /**
     *
     * @param inputStream
     * @return content of the file as String
     * @throws IOException
     */
    public static String readFromInputStream(InputStream inputStream)
        throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                 = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
