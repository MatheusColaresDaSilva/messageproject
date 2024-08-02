package com.smbackoffice.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponseDTO {
    private Long id;
    private String name;
    private String cpf;
    private Integer phoneNumber;
    private String cnpj;
    private String companyName;
}
