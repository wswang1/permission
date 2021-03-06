package com.niii.permission.common;

public class Result {
    private boolean result;
    private Integer code;
    private String message;
    private Object data;

    public Result(boolean result, Integer code, String message, Object data) {
        this.result = result;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean result, Integer code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public Result() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
