package com.smsender.config;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayloadSmSenderDTO implements Serializable {
    private Long idClient;
    private Long idPlan;
    private Long idMessage;
}
