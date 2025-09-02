package com.example.mall.util;

public class CustomJWTException extends RuntimeException {
    public CustomJWTException(String message) {
        super(message);
    }
}