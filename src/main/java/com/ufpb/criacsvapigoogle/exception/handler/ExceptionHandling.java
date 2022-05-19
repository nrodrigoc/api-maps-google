package com.ufpb.criacsvapigoogle.exception.handler;

import com.ufpb.criacsvapigoogle.exception.zalando.ProblemKey;
import feign.FeignException;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.violations.ConstraintViolationProblem;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandling implements ProblemHandling {

    private final MessageSource messageSource;

    public ExceptionHandling(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public ResponseEntity<Problem> process(ResponseEntity<Problem> entity, NativeWebRequest request) {
        if (entity == null || entity.getBody() == null) {
            return entity;
        }

        Problem problem = entity.getBody();
        if (!(problem instanceof ConstraintViolationProblem || problem instanceof DefaultProblem)) {
            return entity;
        }

        ProblemBuilder builder = Problem.builder()
                .withStatus(problem.getStatus())
                .withTitle(problem.getTitle())
                .with("path", request.getNativeRequest(HttpServletRequest.class).getRequestURI());

        if (problem instanceof ConstraintViolationProblem) {
            builder
                    .with("violations", ((ConstraintViolationProblem) problem).getViolations())
                    .withDetail(messageSource.getMessage(ProblemKey.REQUISICAO_INVALIDA.name(),null, null))
                    .with("message", ProblemKey.REQUISICAO_INVALIDA);
            return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
        } else {
            builder
                    .withCause(((DefaultProblem) problem).getCause())
                    .withDetail(problem.getDetail())
                    .withInstance(problem.getInstance());
            problem.getParameters().forEach(builder::with);
            if (!problem.getParameters().containsKey("message") && problem.getStatus() != null) {
                builder.with("message", problem.getStatus().getStatusCode() != 500 ? problem.getStatus() + "_ERROR" : problem.getStatus().toString());
            }
            return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
        }
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Problem> handleConnectionException(FeignException ex, NativeWebRequest request){
        Problem problem = createProblem(Status.INTERNAL_SERVER_ERROR, ProblemKey.ERRO_COMUNICACAO_API_EXTERNA);
        return create(ex, problem, request);
    }

    private Problem createProblem(Status status, ProblemKey problemKey){
        return Problem.builder()
                .withStatus(status)
                .withTitle(status.getReasonPhrase())
                .withDetail(messageSource.getMessage(problemKey.name(), null, status.getReasonPhrase(), null))
                .with("message", problemKey)
                .build();
    }
}
