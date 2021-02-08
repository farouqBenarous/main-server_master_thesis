package com.controllers.handler;

import com.entity.entities.BasicError;
import com.entity.exceptions.BadRequestException;
import com.entity.exceptions.ForbiddenException;
import com.entity.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<BasicError> forbiddenExceptionHandler(ForbiddenException e , HttpServletRequest request){
        BasicError error = new BasicError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<BasicError> objectNotFoundExceptionHanlder(ObjectNotFoundException e , HttpServletRequest request){
        BasicError error = new BasicError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BasicError> badRequestExceptionHandler(BadRequestException e , HttpServletRequest request){
        BasicError error = new BasicError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
