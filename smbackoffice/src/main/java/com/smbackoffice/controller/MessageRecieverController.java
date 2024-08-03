package com.smbackoffice.controller;

import com.smbackoffice.dto.request.DeductionRequestDTO;
import com.smbackoffice.exception.MessageNotFoundException;
import com.smbackoffice.service.RequestDeductionAcconuntClientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/deduction")
public class MessageRecieverController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(MessageRecieverController.class);

    private RequestDeductionAcconuntClientService requestDeductionAcconuntClientService;

    public MessageRecieverController(RequestDeductionAcconuntClientService messageService) {
        this.requestDeductionAcconuntClientService = messageService;
    }

    @PostMapping
    public ResponseEntity<String> deductAccount(@RequestBody @Valid DeductionRequestDTO deductionRequestDTO) {
        try {
            requestDeductionAcconuntClientService.deductionAccount(deductionRequestDTO);
            return ResponseEntity.ok("OK");
        } catch (MessageNotFoundException e) {
            logger.error("Failed to deduct account: ", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
        } catch (Exception e) {
            logger.error("Failed to deduct account: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL");
        }
    }

}
