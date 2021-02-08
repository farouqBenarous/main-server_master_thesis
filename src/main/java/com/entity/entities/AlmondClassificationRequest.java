package com.entity.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class AlmondClassificationRequest implements Serializable {
    private static final long serialVersionUID = -1066877772123933415L;

    @JsonProperty("instances")
    private List<Object> instances;

    public AlmondClassificationRequest() {
    }

    public AlmondClassificationRequest(List<Object> instances) {
        this.instances = instances;
    }

    public List<Object> getInstances() {
        return instances;
    }

    public void setInstances(List<Object> instances) {
        this.instances = instances;
    }
}
