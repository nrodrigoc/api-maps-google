package com.ufpb.criacsvapigoogle.exception;

import com.ufpb.criacsvapigoogle.exception.zalando.ProblemKey;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

public class APIExternaAlertException extends AlertException{

    private static final StatusType STATUS = Status.INTERNAL_SERVER_ERROR;

    public APIExternaAlertException(ProblemKey problemKey) {
        super(STATUS, problemKey);
    }

    public APIExternaAlertException(ProblemKey problemKey, Object... messageArgs) {
        super(STATUS, problemKey, messageArgs);
    }
}
