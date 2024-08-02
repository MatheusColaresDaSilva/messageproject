package com.smbackoffice.controller;

import com.smbackoffice.exception.BusinessException;
import com.smbackoffice.dto.response.ErroResponseDTO;
import com.smbackoffice.dto.response.ResponseDTO;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public abstract class BaseController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseDTO<Object>> handleException(BusinessException ex) {
        var erroResponseDTO = new ErroResponseDTO(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDTO.withErro(erroResponseDTO));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseDTO<Object>> handleException(ConstraintViolationException ex) {
        var erroResponseDTO = new ErroResponseDTO(ex.getMessage().split("messageTemplate='")[1].split("'}")[0]);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDTO.withErro(erroResponseDTO));
    }

}
