package com.lizheng.play.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 李政
 * 2017/12/14
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(Exception exception) {

        if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
            StringBuilder stringBuilder = new StringBuilder();
            Set<ConstraintViolation<?>> constraintViolationSet = constraintViolationException.getConstraintViolations();
            constraintViolationSet.forEach(one -> {System.out.println(one.getMessage());stringBuilder.append(one
                    .getMessage()).append("------");});

            return stringBuilder.toString();
        }

        System.out.println("exception message: " + exception.getMessage());

        return "exception type： " + exception.getClass();
    }
}
