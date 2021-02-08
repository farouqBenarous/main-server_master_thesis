package com.entity.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ClassificationResponse implements Serializable {
    private static final long serialVersionUID = -1027333772123933415L;

    @JsonProperty("entity")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String entity;

    @JsonProperty("entityClass")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object entityClass;


    @JsonProperty("score")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String score;

    public ClassificationResponse() {
    }

    public ClassificationResponse(String entity, Object entityClass, String score) {
        this.entity = entity;
        this.entityClass = entityClass;
        this.score = score;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Object getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Object entityClass) {
        this.entityClass = entityClass;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public static String toPercentage(Double number) {
        return Math.ceil(number * 100) + "%";
    }
}
