package co.ratethem.controller.rest_exception_handler;

import co.ratethem.controller.rest_exception_handler.error.ApiError;
import co.ratethem.controller.rest_exception_handler.exception.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        log.warn(error);
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }


    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        String error = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(error);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EmptyValueException.class)
    protected ResponseEntity<Object> handleEmptyValueException(EmptyValueException ex) {
        String error = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(error);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidValueException.class)
    protected ResponseEntity<Object> handleInvalidValueException(InvalidValueException ex) {
        String error = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(error);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN);
        apiError.setMessage(error);

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(error);

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidDomainNameException.class)
    protected ResponseEntity<Object> handleInvalidDomainNameException(InvalidDomainNameException ex) {
        String error = ex.getMessage();
        String debugError = "Reason:"+ex.getReason()+", Input:"+ex.getInput()+", At Index:"+ex.getIndex();

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(error);
        apiError.setDebugMessage(debugError);

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    protected ResponseEntity<Object> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException ex) {
        String error = ex.getMessage();
        String debugError = "Actual size:"+ex.getActualSize()+", Expected size:"+ex.getExpectedSize();

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(error);
        apiError.setDebugMessage(debugError);

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AssessmentNotFoundException.class)
    protected ResponseEntity<Object> handleAssessmentNotFoundException(AssessmentNotFoundException ex) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(error);

        return buildResponseEntity(apiError);
    }

/*
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleAssessmentNotFoundException(BadCredentialsException ex) {
        String error = ex.getMessage();

        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN);
        apiError.setMessage(error);

        return buildResponseEntity(apiError);
    }
*/



    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        log.warn(apiError.getMessage());

        if(apiError.getDebugMessage()!=null) {
            log.warn(apiError.getDebugMessage());
        }

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}