package com.finp.moic.util.exception;

import com.finp.moic.util.exception.list.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<BusinessExceptionEntity> exceptionHandler(HttpServletRequest request, final BusinessException e){
        return ResponseEntity.status(e.getError().getStatus())
                .body(BusinessExceptionEntity.builder()
                        .errorCode(e.getError().getErrorCode())
                        .errorMessage(e.getError().getErrorMessage())
                        .build());
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<BusinessExceptionEntity> exceptionHandler(HttpServletRequest request, final ValidationException e){
        return ResponseEntity.status(e.getError().getStatus())
                .body(BusinessExceptionEntity.builder()
                        .errorCode(e.getError().getErrorCode())
                        .errorMessage(e.getError().getErrorMessage())
                        .build());
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<BusinessExceptionEntity> exceptionHandler(HttpServletRequest request, final NotFoundException e){
        return ResponseEntity.status(e.getError().getStatus())
                .body(BusinessExceptionEntity.builder()
                        .errorCode(e.getError().getErrorCode())
                        .errorMessage(e.getError().getErrorMessage())
                        .build());
    }

    @ExceptionHandler({AlreadyExistException.class})
    public ResponseEntity<BusinessExceptionEntity> exceptionHandler(HttpServletRequest request, final AlreadyExistException e){
        return ResponseEntity.status(e.getError().getStatus())
                .body(BusinessExceptionEntity.builder()
                        .errorCode(e.getError().getErrorCode())
                        .errorMessage(e.getError().getErrorMessage())
                        .build());
    }

    @ExceptionHandler({DeniedException.class})
    public ResponseEntity<BusinessExceptionEntity> exceptionHandler(HttpServletRequest request, final DeniedException e){
        return ResponseEntity.status(e.getError().getStatus())
                .body(BusinessExceptionEntity.builder()
                        .errorCode(e.getError().getErrorCode())
                        .errorMessage(e.getError().getErrorMessage())
                        .build());
    }



    @ExceptionHandler({TokenException.class})
    public ResponseEntity<BusinessExceptionEntity> exceptionHandler(HttpServletRequest request, final TokenException e){
        return ResponseEntity.status(e.getError().getStatus())
                .body(BusinessExceptionEntity.builder()
                        .errorCode(e.getError().getErrorCode())
                        . errorMessage(e.getError().getErrorMessage())
                        .build());
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<BusinessExceptionEntity> exceptionHandler(HttpServletRequest request, final MethodArgumentNotValidException e) {
        return ResponseEntity.status(ExceptionEnum.NOT_VALID_ERROR.getStatus())
                .body(BusinessExceptionEntity.builder()
                        .errorCode(ExceptionEnum.NOT_VALID_ERROR.getErrorCode())
                        .errorMessage(ExceptionEnum.NOT_VALID_ERROR.getErrorMessage())
                        .build());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<BusinessExceptionEntity> exceptionHandler(HttpServletRequest request, final ConstraintViolationException e) {
        return ResponseEntity.status(ExceptionEnum.NOT_VALID_ERROR.getStatus())
                .body(BusinessExceptionEntity.builder()
                        .errorCode(ExceptionEnum.NOT_VALID_ERROR.getErrorCode())
                        .errorMessage(ExceptionEnum.NOT_VALID_ERROR.getErrorMessage())
                        .build());
    }
}
