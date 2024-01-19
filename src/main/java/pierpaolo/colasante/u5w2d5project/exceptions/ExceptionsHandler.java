package pierpaolo.colasante.u5w2d5project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pierpaolo.colasante.u5w2d5project.payloads.ErrorDTO;
import pierpaolo.colasante.u5w2d5project.payloads.ErrorDTOwithList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400 Bad Request
    public ErrorDTOwithList handleBadRequest(BadRequestException ex){
        List<String> errMessage = new ArrayList<>();
        if(ex.getErrorList() != null)
            errMessage = ex.getErrorList().stream().map(err -> err.getDefaultMessage()).toList();
        return new ErrorDTOwithList(ex.getMessage(), LocalDateTime.now(), errMessage);
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)   //404 Not Found
    public ErrorDTO HandleNotFound(NotFoundException ex){
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   //500 Internal Server Error
    public ErrorDTO handleGenericError(Exception ex){
        ex.printStackTrace();
        return new ErrorDTO("*** problema Lato Server *** Giuro che risolveremo!!!!", LocalDateTime.now());
    }
}
