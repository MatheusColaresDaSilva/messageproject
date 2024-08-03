package com.smsender.dto.request;

import com.smsender.enums.PlanType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequestDTO {
    @NotNull(message = "Must have a name")
    private String name;
    @NotNull(message = "Must have a cpf")
    private String cpf;
    @NotNull(message = "Must have a phonenumber")
    private Integer phoneNumber;
    @NotNull(message = "Must have a cnpj")
    private String cnpj;
    @NotNull(message = "Must have a company name")
    private String companyName;
    @NotNull(message = "Must have a plantype")
    private PlanType planType;
}
