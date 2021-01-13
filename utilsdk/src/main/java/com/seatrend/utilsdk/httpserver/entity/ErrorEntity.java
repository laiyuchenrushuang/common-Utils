package com.seatrend.utilsdk.httpserver.entity;

/**
 * Created by seatrend on 2018/8/22.
 */

public class ErrorEntity {


    /**
     * timestamp : 1534930699812
     * status : 500
     * error : Internal Server Error
     * exception : feign.RetryableException
     * message : Connection refused: connect executing GET http://localhost:8081/vio/getVioByCar?hpzl=02&hphm=%E5%B7%9DAJL122&clsbdh=1234
     * path : /vio/getVioByCar
     */

    private long timestamp;
    private int status;
    private String error;
    private String exception;
    private String message;
    private String path;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ErrorEntity{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", exception='" + exception + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
