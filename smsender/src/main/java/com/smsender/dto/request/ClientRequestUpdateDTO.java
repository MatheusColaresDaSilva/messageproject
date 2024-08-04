package com.smsender.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequestUpdateDTO {
    private String name;
    private String cpf;
    private Long phoneNumber;
    private String cnpj;
    private String companyName;
}
