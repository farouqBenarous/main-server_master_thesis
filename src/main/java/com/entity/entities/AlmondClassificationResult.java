package com.entity.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class AlmondClassificationResult implements Serializable {
    private static final long serialVersionUID = -1027877772123933415L;

    @JsonProperty("predictions")
    private List<Object> predictions;

    public AlmondClassificationResult() {
    }

    public AlmondClassificationResult(List<Object> predictions) {
        this.predictions = predictions;
    }

    public List<Object> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Object> predictions) {
        this.predictions = predictions;
    }
}
