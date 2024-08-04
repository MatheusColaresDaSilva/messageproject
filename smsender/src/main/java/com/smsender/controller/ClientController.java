package com.smsender.controller;

import com.smsender.dto.request.ClientRequestDTO;
import com.smsender.dto.request.ClientRequestUpdateDTO;
import com.smsender.dto.response.ClientResponseDTO;
import com.smsender.dto.response.ResponseDTO;
import com.smsender.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/client")
public class ClientController extends BaseController{

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<ClientResponseDTO>> findById(@PathVariable("id") Long id) {
        ClientResponseDTO response = clientService.findById(id);
        return ResponseEntity.ok(new ResponseDTO<>(response));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<Page<ClientResponseDTO>>> findAll(Pageable page) {
        Page<ClientResponseDTO> response = clientService.findAll(page);
        return ResponseEntity.ok(new ResponseDTO<>(response));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<ClientResponseDTO>> createClientWithPlan(@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO response = clientService.createClient(clientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<ClientResponseDTO>> updateClient(@RequestBody ClientRequestUpdateDTO clientRequestUpdateDTO, @PathVariable("id") Long id) {
        ClientResponseDTO response = clientService.updateClient(clientRequestUpdateDTO, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO<>(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
