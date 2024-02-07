package ex.wookis.mvc2.controller.advice;

import ex.wookis.mvc2.domain.ErrorResult;
import ex.wookis.mvc2.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("ex.wookis.mvc2.controller")
public class MyControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MemberException.class)
    public ErrorResult memberExHandler(MemberException e) {
        log.error("[Member Exception] e ->", e);
        return new ErrorResult("사용자 오류", e.getMessage());
    }

}
