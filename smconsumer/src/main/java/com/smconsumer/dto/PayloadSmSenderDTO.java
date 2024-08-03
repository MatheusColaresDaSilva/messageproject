package com.smconsumer.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayloadSmSenderDTO implements Serializable {
    private Long idAccount;
    private Long idMessage;
}
