package com.smsender.service;

import com.smsender.dto.request.ClientRequestDTO;
import com.smsender.dto.request.ClientRequestUpdateDTO;
import com.smsender.dto.response.ClientResponseDTO;
import com.smsender.entity.Client;
import com.smsender.entity.ClientPlan;
import com.smsender.enums.PlanType;
import com.smsender.exception.ClientNotFoundException;
import com.smsender.repository.ClientRepository;
import com.smsender.utils.ValidationsUtils;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.smsender.converter.ClientConverter.clientDtoToEntity;
import static com.smsender.converter.ClientConverter.clientEntityToDto;

@Service
public class ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    private ClientRepository clientRepository;
    private PlanService planService;

    public ClientService(ClientRepository clientRepository, PlanService planService) {
        this.clientRepository = clientRepository;
        this.planService = planService;
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

        try {
            final Client client = clientDtoToEntity(clientRequestDTO);
            clientRepository.save(client);
            createNewPlanClient(client, clientRequestDTO.getPlanType());
            return clientEntityToDto(client);
        } catch (ConstraintViolationException e) {
            logger.error("Failed to create new Client: ", e.getMessage());
        }

        return null;

    }

    @Transactional
    private ClientPlan createNewPlanClient(Client client, PlanType plantype) {
        return planService.createNewClientPlan(client, plantype);
    }


    @Transactional
    public ClientResponseDTO updateClient(ClientRequestUpdateDTO clientRequestUpdateDTO, Long id) {
        final Client client = getClientById(id);
        final Client clientUpdated = updateContentClientDtoToEntity(clientRequestUpdateDTO, client);

        return clientEntityToDto(clientRepository.save(clientUpdated));
    }

    @Transactional
    public void deleteClient(Long id) {
        final Client pessoa = getClientById(id);
        clientRepository.delete(pessoa);
    }

    private Client updateContentClientDtoToEntity(ClientRequestUpdateDTO clientRequestUpdateDTO, Client client) {

        Optional.ofNullable(clientRequestUpdateDTO.getName()).ifPresent(client::setName);
        Optional.ofNullable(clientRequestUpdateDTO.getCpf()).ifPresent(client::setCpf);
        Optional.ofNullable(clientRequestUpdateDTO.getCompanyName()).ifPresent(client::setCompanyName);
        Optional.ofNullable(clientRequestUpdateDTO.getCnpj()).ifPresent(client::setCnpj);
        Optional.ofNullable(clientRequestUpdateDTO.getPhoneNumber()).ifPresent(client::setPhoneNumber);

        return client;
    }

    private Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException());
    }
}
