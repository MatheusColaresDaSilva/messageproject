package com.smsender.controller;

import com.smsender.dto.request.MessageRequestDTO;
import com.smsender.dto.response.MessageResponseDTO;
import com.smsender.dto.response.ResponseDTO;
import com.smsender.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController extends BaseController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<MessageResponseDTO>> findById(@PathVariable Long id) {
        MessageResponseDTO response = messageService.findById(id);
        return ResponseEntity.ok(new ResponseDTO<>(response));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<Page<MessageResponseDTO>>> findAllMessage(Pageable page) {
        Page<MessageResponseDTO> response = messageService.findAll(page);
        return ResponseEntity.ok(new ResponseDTO<>(response));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<MessageResponseDTO>> createNewMessage(@RequestBody MessageRequestDTO messageRequestDTO) {
        MessageResponseDTO response = messageService.createNewMessage(messageRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(response));
    }

}
