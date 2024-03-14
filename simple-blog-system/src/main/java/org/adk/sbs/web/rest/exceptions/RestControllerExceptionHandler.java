package org.adk.sbs.web.rest.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.adk.sbs.dto.CustomErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

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
        return super.handleExceptionInternal(
                ex,
                new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null),
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
