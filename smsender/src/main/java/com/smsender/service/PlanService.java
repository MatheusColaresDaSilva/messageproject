package com.smsender.service;

import com.smsender.entity.*;
import com.smsender.enums.PlanType;
import com.smsender.exception.PlanNotFoundException;
import com.smsender.repository.ClientPlanRepository;
import com.smsender.repository.PlanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.smsender.converter.ClientConverter.clientEntityToDto;

@Service
public class PlanService {

    private PlanRepository planRepository;
    private ClientPlanRepository clientPlanRepository;
    private AccountService accountService;

    public PlanService(PlanRepository planRepository, ClientPlanRepository clientPlanRepository, AccountService accountService) {
        this.planRepository = planRepository;
        this.clientPlanRepository = clientPlanRepository;
        this.accountService = accountService;
    }

    public Plan getPlanByType(PlanType type) {
        return planRepository.findByType(type).orElseThrow(() -> new PlanNotFoundException());
    }

    public Plan getPlanById(Long id) {
        return planRepository.findById(id).orElseThrow(() -> new PlanNotFoundException());
    }

    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    public ClientPlan createNewClientPlan(Client client, PlanType planType) {

        ClientPlan clientPlan = new ClientPlan();
        ClientPlanId clientPlanId = new ClientPlanId(client, getPlanByType(planType));

        Account account = accountService.createNewAccountClient(clientPlanId);
        clientPlan.setId(clientPlanId);
        clientPlan.setAccount(account);
        return clientPlanRepository.save(clientPlan);
    }
}
