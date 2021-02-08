package com.entity.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlmondClassificationResponse extends ClassificationResponse implements Serializable {
    private static final long serialVersionUID = -1011177772155533415L;

    @JsonProperty("predictions")
    List<ClassificationResponse> predictions = new ArrayList<>();;

    public AlmondClassificationResponse() {
    }

    public List<ClassificationResponse> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<ClassificationResponse> predictions) {
        this.predictions = predictions;
    }

    public void addPrediction(ClassificationResponse response) {
        predictions.add(response);
    }}
