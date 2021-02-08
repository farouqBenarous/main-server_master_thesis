package com.controllers;


import com.entity.entities.AlmondClassificationResponse;
import com.entity.entities.AlmondClassificationResult;
import com.entity.entities.AlmondProcessingResult;
import com.entity.entities.User;
import com.logic.AlmondService;
import com.logic.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class MainController {

    @Autowired
    AlmondService almondService;

    @Autowired
    UserService userService;

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @PostMapping(value = "/almonds/classifier")
    public ResponseEntity<Object> classifyAlmondImage(@RequestParam("image") MultipartFile image, Integer limit) throws IOException {

        AlmondProcessingResult processed = almondService.process(image);

        AlmondClassificationResponse classificationResult = almondService.classify(processed, limit);

        if (processed != null) {
            return ResponseEntity.status(HttpStatus.OK).body(classificationResult);
        }

        log.error(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @PostMapping(value = "/create-user")
    public ResponseEntity createUser(@RequestBody User user) {

        String admin_username = SecurityContextHolder.getContext().getAuthentication().getName();
        User retUser = userService.createUser(user, admin_username);
        if (retUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body(retUser);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Weren't possible create user with username: %s", user.getEmail()));
        }
    }

}
