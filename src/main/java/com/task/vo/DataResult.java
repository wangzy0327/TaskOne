package com.task.vo;

public class DataResult<T> {
    //请求是否成功
    private boolean success;
    private T data;
    private String error;

    public DataResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public DataResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
