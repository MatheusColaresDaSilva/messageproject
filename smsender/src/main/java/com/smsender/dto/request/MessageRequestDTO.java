package com.smsender.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageRequestDTO {
    @NotNull(message = "Must have a client id")
    private Long clientId;
    @NotNull(message = "Must have a plan id")
    private Long planId;
    @NotNull(message = "Must have a content")
    private String content;
    @NotNull(message = "Must have a recipient phone")
    private Integer recipientPhoneNumber;
    private Boolean isWhats;
}
