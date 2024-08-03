package com.smbackoffice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeductionRequestDTO {
    @NotNull(message = "Must have a account id")
    private Long idAccount;
    @NotNull(message = "Must have a message id")
    private Long idMessage;
}
