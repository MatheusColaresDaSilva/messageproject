package com.smbackoffice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawRequestDTO {
    @NotNull(message = "Must have a client id")
    private Long idClient;
    @NotNull(message = "Must have a plan id")
    private Long idPlan;
    @NotNull(message = "Must have a message id")
    private Long idMessage;
}
