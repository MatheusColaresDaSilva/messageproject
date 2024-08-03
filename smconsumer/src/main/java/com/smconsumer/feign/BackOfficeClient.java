package com.smconsumer.feign;

import com.smconsumer.dto.PayloadSmSenderDTO;
import com.smconsumer.dto.WithdrawResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "smbackoffice", url = "http://localhost:8082")
public interface BackOfficeClient {

    @PostMapping("/api/v1/deduction")
    WithdrawResponseDTO evaluateRequestMessage(@RequestBody PayloadSmSenderDTO payload);
}
