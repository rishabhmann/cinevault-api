package com.cinevault.exception;

public class ResourceNotFoundException extends RuntimeException {

    /*
    Exception will pass this message and send it to parent exception using super() using this constructor
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}