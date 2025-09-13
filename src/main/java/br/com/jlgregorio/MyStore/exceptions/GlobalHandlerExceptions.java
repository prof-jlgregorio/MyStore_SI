package br.com.jlgregorio.MyStore.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalHandlerExceptions {

    @ExceptionHandler(Exception.class)
    public final CustomExceptionResponse handleAllExceptions(
            Exception ex, WebRequest request
    ){
        CustomExceptionResponse response = new CustomExceptionResponse(new Date(),
                ex.getMessage(), request.getDescription(true));
        return response;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final CustomExceptionResponse handleResourceNotFoundException(
            Exception ex, WebRequest request
    ){
        CustomExceptionResponse response = new CustomExceptionResponse( new Date(),
                "Recurso não encontrado!", request.getDescription(true));
        return response;
    }

}
