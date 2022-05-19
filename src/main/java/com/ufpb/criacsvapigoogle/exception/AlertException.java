package com.ufpb.criacsvapigoogle.exception;

import com.ufpb.criacsvapigoogle.exception.zalando.ProblemKey;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.StatusType;

public class AlertException extends AbstractThrowableProblem {

    private final ProblemKey problemKey;

    private Object[] messageArgs;

    public AlertException(StatusType status, ProblemKey problemKey) {
        super(null, null, status);
        this.problemKey = problemKey;
    }

    public AlertException(StatusType status, ProblemKey problemKey, Object... messageArgs) {
        super(null, null, status);
        this.problemKey = problemKey;
        this.messageArgs = messageArgs;
    }

    public ProblemKey getProblemKey() {
        return problemKey;
    }

    public Object[] getMessageArgs() {
        return messageArgs;
    }
}
