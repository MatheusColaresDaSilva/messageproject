package com.smconsumer.feign;

import com.smconsumer.dto.PayloadSmSenderDTO;
import com.smconsumer.dto.DeductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "smbackoffice", url = "${feign.client.config.smbackoffice.url}")
public interface BackOfficeClient {

    @PostMapping("/api/v1/deduction")
    DeductResponseDTO evaluateRequestMessage(@RequestBody PayloadSmSenderDTO payload);
}
