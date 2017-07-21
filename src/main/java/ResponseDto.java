package main.java;

import javax.ws.rs.core.Response;

/**
 * Created by davits on 7/20/17.
 */
class ResponseDto {

    // response message
    private String message;

    // response status
    private Response.Status status;


    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(final Response.Status status) {
        this.status = status;
    }

}
