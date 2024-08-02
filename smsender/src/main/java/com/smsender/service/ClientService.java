package com.smsender.service;

import com.smsender.dto.request.ClientRequestDTO;
import com.smsender.dto.response.ClientResponseDTO;
import com.smsender.entity.Client;
import com.smsender.exception.ClientNotFoundException;
import com.smsender.repository.ClientRepository;
import com.smsender.utils.ValidationsUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.smsender.converter.ClientConverter.*;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<ClientResponseDTO> findAll(Pageable page) {
        List<ClientResponseDTO> clientResponse = new ArrayList<>();

        Page<Client> client = clientRepository.findAll(page);
        client.forEach(pessoa -> clientResponse.add(clientEntityToDto(pessoa)));

        return  new PageImpl<>(clientResponse, page, client.getTotalElements()) ;

    }

    public ClientResponseDTO findById(Long id) {
        final Client client = getClientById(id);
        return clientEntityToDto(client);
    }

    @Transactional
    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        ValidationsUtils.isCpfValido(clientRequestDTO.getCpf());

        final Client client = clientDtoToEntity(clientRequestDTO);
        return clientEntityToDto(clientRepository.save(client));
    }


    @Transactional
    public ClientResponseDTO updateClient(ClientRequestDTO pessoaRequestDTO, Long id) {
        final Client client = getClientById(id);
        final Client clientUpdated = updateContentClientDtoToEntity(pessoaRequestDTO, client);

        return clientEntityToDto(clientRepository.save(clientUpdated));
    }

    @Transactional
    public void deleteClient(Long id) {
        final Client pessoa = getClientById(id);
        clientRepository.delete(pessoa);
    }

    private Client updateContentClientDtoToEntity(ClientRequestDTO clientRequestDTO, Client client) {

        Optional.ofNullable(clientRequestDTO.getName()).ifPresent(client::setName);
        Optional.ofNullable(clientRequestDTO.getCpf()).ifPresent(client::setCpf);
        Optional.ofNullable(clientRequestDTO.getCompanyName()).ifPresent(client::setCompanyName);
        Optional.ofNullable(clientRequestDTO.getCnpj()).ifPresent(client::setCnpj);
        Optional.ofNullable(clientRequestDTO.getPhoneNumber()).ifPresent(client::setPhoneNumber);

        return client;
    }

    private Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException());
    }


}
