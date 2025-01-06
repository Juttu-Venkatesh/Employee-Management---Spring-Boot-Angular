package com.employee.dto;

public class ResponseDTO {

    private String message;
    private boolean result;
    private Object data;

    // Constructor
    public ResponseDTO(String message, boolean result, Object data) {
        this.message = message;
        this.result = result;
        this.data = data;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
