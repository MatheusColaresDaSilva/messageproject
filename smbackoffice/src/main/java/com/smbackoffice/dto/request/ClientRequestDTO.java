package com.smbackoffice.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequestDTO {
    private String name;
    private String cpf;
    private Integer phoneNumber;
    private String cnpj;
    private String companyName;
}
