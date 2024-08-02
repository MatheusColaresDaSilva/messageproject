package com.smsender.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponseDTO {
    private Long id;
    private String content;
    private Integer recipientPhoneNumber;
    private Boolean isWhats;
    private String status;
}
