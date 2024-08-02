package com.smbackoffice.controller;

import com.smbackoffice.dto.request.WithdrawRequestDTO;
import com.smbackoffice.dto.response.WithdrawResponseDTO;
import com.smbackoffice.service.RequestMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/withdraw")
public class MessageRecieverController extends BaseController {

    private RequestMessageService messageService;

    public MessageRecieverController(RequestMessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public WithdrawResponseDTO evaluateRequestMessage(@RequestBody WithdrawRequestDTO withdrawRequestDTO) {
        //MessageResponseDTO response = messageService.createNewMessage(messageRequestDTO);
        System.out.println("testing");
        return new WithdrawResponseDTO();
    }

}
