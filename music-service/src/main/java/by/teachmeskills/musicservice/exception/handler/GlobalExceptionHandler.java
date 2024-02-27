package by.teachmeskills.musicservice.exception.handler;

import by.teachmeskills.musicservice.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("VALIDATION_FAILED", "The request was not validated");
        e.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errorResponse.addField(fieldError.getField(), fieldError.getDefaultMessage()));
        return errorResponse;
    }
}
