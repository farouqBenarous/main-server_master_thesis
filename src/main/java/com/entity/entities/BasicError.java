package com.entity.entities;


import java.io.Serializable;


public class BasicError implements Serializable {

    private static final long serialVersionUID = 4305818140214768467L;
    private Integer status;
    private String msg;
    private Long timeStamp;

    public BasicError(Integer status, String msg, Long timeStamp) {
        super();
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
