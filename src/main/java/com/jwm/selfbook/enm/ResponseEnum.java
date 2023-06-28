package com.jwm.selfbook.enm;

import org.springframework.http.HttpStatus;

/**
 * This ENUM represents the static response String and HttpStatus value associated with the different scenarios.
 */
public enum ResponseEnum {

    MOVIE_NOT_FOUND(HttpStatus.NOT_FOUND, "Movie Not Found - Please try again"),
    NO_SUGGESTIONS(HttpStatus.OK, "You are all caught up!  Thank you for your business and stay tuned for new titles!");

    private HttpStatus httpStatus;
    private String response;

    ResponseEnum(final HttpStatus httpStatus, final String response) {
        this.httpStatus = httpStatus;
        this.response = response;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
