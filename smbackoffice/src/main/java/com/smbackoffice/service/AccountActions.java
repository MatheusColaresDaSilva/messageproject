package com.smbackoffice.service;

import com.smbackoffice.dto.request.DeductionRequestDTO;

public interface AccountActions {

    void deduceValueFromAccount(DeductionRequestDTO withdrawRequestDTO);
}
