package com.dodoworkshop.climbyapi.exception.type.api;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(Class<?> resourceClass) {
        super(HttpStatus.NOT_FOUND, "%s has not been found".formatted(resourceClass.getSimpleName()));
    }
}
