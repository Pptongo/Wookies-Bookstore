package com.wookie.bookstore.response;

/**
 * Generic object to pass as response for all web services.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public class ApiResponse {
    
    /**
     * The error message in case of the web service fail.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String message;

    /**
     * Store any object obtained from the service to be used for client.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private Object body;

    /**
     * The actual status of the process.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private Boolean status;

    /**
     * Create an empty ApiResponse.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    public ApiResponse() { }

    /**
     * Create an ApiResponse with the given status.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param status The current status of the operation.
     */
    public ApiResponse(Boolean status) {
        this.status = status;
    }

    /**
     * Create a new ApiResponse with the given body and set the status as true by default.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param body The object to be added in the response.
     */
    public ApiResponse(Object body) {
        this.status = true;
        this.body = body;
        this.message = null;
    }

    /**
     * Create a new ApiResponse with the given body and message and set the status as true by default.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param body The object to be added for the response.
     * @param message Set message to inform the client extra information.
     */
    public ApiResponse(Object body, String message) {
        this.status = true;
        this.body = body;
        this.message = message;
    }

    /**
     * Create a new ApiResponse with the given error message and set the status as false by default.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param message
     */
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
