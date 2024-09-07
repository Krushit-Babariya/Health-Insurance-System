package com.krushit.exception_ed;

public class PlanAlreadyAppliedException extends RuntimeException {
    public PlanAlreadyAppliedException(String message) {
        super(message);
    }
}

