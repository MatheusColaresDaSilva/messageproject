package com.smsender.converter;

import com.smsender.dto.request.ClientRequestDTO;
import com.smsender.dto.response.ClientResponseDTO;
import com.smsender.entity.Client;

public abstract class ClientConverter {

        public static Client clientDtoToEntity(ClientRequestDTO clientRequestDTO) {
            return Client.builder()
            .name(clientRequestDTO.getName())
            .cpf(clientRequestDTO.getCpf())
            .companyName(clientRequestDTO.getCompanyName())
            .cnpj(clientRequestDTO.getCnpj())
            .phoneNumber(clientRequestDTO.getPhoneNumber())
            .build();
        }

        public static ClientResponseDTO clientEntityToDto(Client client) {
            return ClientResponseDTO.builder()
                    .id(client.getId())
                    .name(client.getName())
                    .cpf(client.getCpf())
                    .companyName(client.getCompanyName())
                    .cnpj(client.getCnpj())
                    .phoneNumber(client.getPhoneNumber())
                    .build();
        }
}
