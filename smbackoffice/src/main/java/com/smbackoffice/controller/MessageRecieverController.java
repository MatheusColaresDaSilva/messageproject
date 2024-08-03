package com.smbackoffice.controller;

import com.smbackoffice.dto.request.DeductionRequestDTO;
import com.smbackoffice.dto.response.WithdrawResponseDTO;
import com.smbackoffice.service.RequestDeductionAcconuntClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/deduction")
public class MessageRecieverController extends BaseController {

    private RequestDeductionAcconuntClientService messageService;

    public MessageRecieverController(RequestDeductionAcconuntClientService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public WithdrawResponseDTO evaluateRequestMessage(@RequestBody @Valid DeductionRequestDTO withdrawRequestDTO) {
        messageService.deductionAccount(withdrawRequestDTO);
        System.out.println("testing");
        return new WithdrawResponseDTO();
    }

}
