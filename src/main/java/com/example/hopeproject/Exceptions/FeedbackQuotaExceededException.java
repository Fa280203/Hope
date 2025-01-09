package com.example.hopeproject.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeedbackQuotaExceededException extends ResponseStatusException {
    public FeedbackQuotaExceededException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
