package com.sample.tokencounter.service;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ErrorException extends AbstractThrowableProblem {

    public ErrorException(String error) {
        super(null, error, Status.UNAUTHORIZED, "Error in service");
    }
}
