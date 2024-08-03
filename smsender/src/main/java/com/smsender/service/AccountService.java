package com.smsender.service;

import com.smsender.dto.response.MessageResponseDTO;
import com.smsender.entity.*;
import com.smsender.enums.PlanType;
import com.smsender.exception.PlanNotFoundException;
import com.smsender.repository.PlanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private PlanRepository planRepository;

    public AccountService(PlanRepository messageRepository) {
        this.planRepository = messageRepository;
    }


    @Transactional
    public Account createNewAccountClient(ClientPlanId clientPlanId) {
        Account account = AccountFactory.createAccount(clientPlanId.getPlan());
        account.addingInitialValue();
        return account;
    }


    private Plan getPlanById(Long id) {
        return planRepository.findById(id).orElseThrow(() -> new PlanNotFoundException());
    }


}
