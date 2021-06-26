package com.wookie.bookstore.response;

public class ApiResponse {
    
    private String message;

    private Object body;

    private Boolean status;

    public ApiResponse() { }

    public ApiResponse(Boolean status) {
        this.status = status;
    }

    public ApiResponse(Object body) {
        this.status = true;
        this.body = body;
        this.message = null;
    }

    public ApiResponse(Object body, String message) {
        this.status = true;
        this.body = body;
        this.message = message;
    }

    public ApiResponse(String message) {
        this.status = false;
        this.message = message;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Object getBody() { return body; }

    public void setBody(Object body) { this.body = body; }

    public Boolean getStatus() { return status; }

    public void setStatus(Boolean status) { this.status = status; }

}
