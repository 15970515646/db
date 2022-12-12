package com.example.demo.utils;

public class Response <T>{
    private Boolean success;

    private String message;

    private T data;

    public Response() {
    }

    public Response(Boolean success) {
        this.success = success;
    }

    public Response(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Response(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}

