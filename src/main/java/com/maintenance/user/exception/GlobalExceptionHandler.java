package com.maintenance.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static com.maintenance.user.util.Constants.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        //ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false));
        ErrorDetail errorDetail = new ErrorDetail(new Date(), webRequest.getDescription(false));
        log.info(exception.getMessage());
        return new ResponseEntity(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpressionException.class)
    public ResponseEntity<?> handleGlobalExpressionException(ExpressionException exception){
        //ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage(), USER_CREATE_FAIL.replace(REPLACE,exception.getExpressionString()));
        ErrorDetail errorDetail = new ErrorDetail(new Date(), USER_CREATE_FAIL.replace(REPLACE,exception.getExpressionString()));
        log.info(exception.getMessage());
        return new ResponseEntity(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        String detail = "Error en atributo: "+exception.getBindingResult().getFieldError().getField()+". "+exception.getBindingResult().getFieldError().getDefaultMessage();
        //ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage(), detail);
        ErrorDetail errorDetail = new ErrorDetail(new Date(), detail);
        log.info(detail);
        return new ResponseEntity(errorDetail, HttpStatus.BAD_REQUEST);
    }

}
