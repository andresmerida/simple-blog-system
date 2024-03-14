package org.adk.sbs.web.rest.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.adk.sbs.dto.CustomErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String VALIDATION_FAILED_CONSTANT = "Validation failed";
    private static final String ERRORS_CONSTANT = "errors";

    public RestControllerExceptionHandler() {
        super();
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleArticleNotFoundException(ArticleNotFoundException ex) {
        return new ResponseEntity<>(
                new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        log.error("RestControllerExceptionHandler::handleExceptionInternal exception caught {}", ex.getMessage());
        if (ex instanceof MethodArgumentNotValidException) {
            List<String> errors = new ArrayList<>();
            ((MethodArgumentNotValidException) ex).getAllErrors()
                    .forEach(objectError -> errors.add(objectError.getDefaultMessage()));

            Map<String, List<String>> result = new HashMap<>();
            result.put(ERRORS_CONSTANT, errors);

            return super.handleExceptionInternal(ex,
                    new CustomErrorResponse(statusCode.value(), VALIDATION_FAILED_CONSTANT, result),
                    headers, statusCode, request);
        }

        return super.handleExceptionInternal(
                ex,
                new CustomErrorResponse(statusCode.value(), ex.getMessage(), null),
                headers,
                statusCode,
                request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGenericException(Exception ex) {
        log.error("RestControllerExceptionHandler::handleGenericException exception caught {}", ex.getMessage());
        return new ResponseEntity<>(
                new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
