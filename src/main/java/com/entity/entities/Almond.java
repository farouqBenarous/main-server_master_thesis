package com.entity.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class Almond implements Serializable {

    private static final long serialVersionUID = -3187980153353374187L;

    @JsonProperty("index")
    String index;

    @JsonProperty("index_one_hot")
    List<Integer> indexOneHot;

    @JsonProperty("title")
    String title;

    public Almond(String index, List<Integer> indexOneHot, String title) {
        this.index = index;
        this.indexOneHot = indexOneHot;
        this.title = title;
    }

    public Almond() {
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<Integer> getIndexOneHot() {
        return indexOneHot;
    }

    public void setIndexOneHot(List<Integer> indexOneHot) {
        this.indexOneHot = indexOneHot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
