package com.smconsumer.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawResponseDTO {
    private Long id;
    private String content;
    private Integer recipientPhoneNumber;
    private Boolean isWhats;
    private String status;
}
