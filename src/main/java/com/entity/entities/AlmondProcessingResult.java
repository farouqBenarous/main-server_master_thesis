package com.entity.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AlmondProcessingResult   implements Serializable {
    private static final long serialVersionUID = -1027812772123939415L;

    @JsonProperty("image_numpy")
    private String imageNumpy;

    @JsonProperty("image_size")
    private String imageSize;

    public AlmondProcessingResult(String imageNumpy, String imageSize) {
        this.imageNumpy = imageNumpy;
        this.imageSize = imageSize;
    }

    public AlmondProcessingResult() {
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageNumpy() {
        return imageNumpy;
    }

    public void setImageNumpy(String imageNumpy) {
        this.imageNumpy = imageNumpy;
    }
}
