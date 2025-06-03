package com.ecommerce.backend.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String massage){
        super(massage);
    }
}
