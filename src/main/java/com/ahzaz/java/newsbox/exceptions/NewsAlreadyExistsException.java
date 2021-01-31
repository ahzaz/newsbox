package com.ahzaz.java.newsbox.exceptions;

/**
 * @author Ahzaz
 */
public class NewsAlreadyExistsException extends Exception {

    private String headline;

    public NewsAlreadyExistsException() {
        super("News Already Exists in database");
    }

    public NewsAlreadyExistsException(String message) {
        headline = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " : " + headline;
    }
}

