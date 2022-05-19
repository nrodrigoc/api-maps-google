package com.ufpb.criacsvapigoogle.exception.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufpb.criacsvapigoogle.exception.APIExternaAlertException;
import com.ufpb.criacsvapigoogle.exception.BadRequestAlertException;
import com.ufpb.criacsvapigoogle.exception.zalando.ProblemKey;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.common.HttpStatusAdapter;

import java.io.InputStream;


public class CustomErrorDecoderMapsAPI implements ErrorDecoder {

    private static final String MESSAGE_FIELD = "message";
    private static final String TITLE_FIELD = "title";

    private ObjectMapper objectMapper;
    private MessageSource messageSource;

    @Autowired
    public void injetaDependencias(ObjectMapper objectMapper, MessageSource messageSource) {
        this.objectMapper = objectMapper;
        this.messageSource = messageSource;
    }

    @Override
    public Exception decode(String s, Response response) {
        try (InputStream bodyIs = response.body().asInputStream()) {
            JsonNode jsonNode = objectMapper.readTree(bodyIs);

            HttpStatus httpStatus = HttpStatus.valueOf(response.status());
            HttpStatusAdapter httpStatusAdapter = new HttpStatusAdapter(httpStatus);
            ThrowableProblem problem;


            if (HttpStatus.INTERNAL_SERVER_ERROR.equals(httpStatus)) {
                problem = Problem.builder()
                        .withStatus(httpStatusAdapter)
                        .withTitle(jsonNode.has(TITLE_FIELD) ? jsonNode.get(TITLE_FIELD).asText() : null)
                        .withDetail(messageSource.getMessage(ProblemKey.ERRO_COMUNICACAO_API_EXTERNA.name(), null, null))
                        .with(MESSAGE_FIELD, jsonNode.has(MESSAGE_FIELD) ? jsonNode.get(MESSAGE_FIELD).asText() : null).build();
            } else {
                problem = Problem.builder().withStatus(httpStatusAdapter)
                        .withTitle(jsonNode.has(TITLE_FIELD) ? jsonNode.get(TITLE_FIELD).asText() : null)
                        .withDetail(jsonNode.has("detail") ? jsonNode.get("detail").asText() : null)
                        .with(MESSAGE_FIELD, jsonNode.has(MESSAGE_FIELD) ? jsonNode.get(MESSAGE_FIELD).asText() : null)
                        .with(MESSAGE_FIELD, jsonNode.has("fieldErrors") ? jsonNode.get("fieldErrors") : null).build();
            }
            return problem;
        } catch (Exception e) {
            switch (response.status()) {
                case 400:
                    return new BadRequestAlertException(ProblemKey.REQUISICAO_INVALIDA);
                default:
                    return new APIExternaAlertException(ProblemKey.ERRO_COMUNICACAO_API_EXTERNA);
            }
        }
    }
}
