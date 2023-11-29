package com.entrepot.senegal.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;
import org.zalando.problem.Status.*;


@JsonInclude(NON_EMPTY)
@JsonIgnoreProperties({"stackTrace", "type", "title", "message", "localizedMessage", "parameters"})
public class CommonException extends AbstractThrowableProblem {

    private CommonException(StatusType status, String detail) {
        super(null, null, status, detail, null, null, null);
    }

    public static CommonException unauthorized() {
        
        return new CommonException(Status.FORBIDDEN, "Unauthorised or Bad Credentials");
    }

    public static CommonException forbidden() {
          
        return new CommonException(Status.FORBIDDEN, "Forbidden");
    }

    public static CommonException headerError() {
        
        return new CommonException(Status.FORBIDDEN, "Missing Header");
    }


}