package com.smsender.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
