package com.smsender.controller;

import com.smsender.dto.response.ErroResponseDTO;
import com.smsender.dto.response.ResponseDTO;
import com.smsender.exception.BusinessException;
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
