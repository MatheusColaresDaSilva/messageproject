package com.smsender.integration;

import com.smsender.dto.request.ClientRequestDTO;
import com.smsender.dto.response.ClientResponseDTO;
import com.smsender.entity.Client;
import com.smsender.entity.ClientPlan;
import com.smsender.entity.Plan;
import com.smsender.enums.PlanType;
import com.smsender.repository.ClientPlanRepository;
import com.smsender.repository.ClientRepository;
import com.smsender.repository.PlanRepository;
import com.smsender.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ClientServiceIntegrationTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private ClientPlanRepository clientPlanRepository;

    @BeforeEach
    public void  createPlan() {
        Plan plan1 = new Plan();
        plan1.setType(PlanType.POS);
        planRepository.save(plan1);

        Plan plan2 = new Plan();
        plan2.setType(PlanType.PRE);
        planRepository.save(plan2);
    }

    @Test
    public void testCreateClientWithAccount() {
        ClientRequestDTO requestDTO = new ClientRequestDTO();
        requestDTO.setCpf("60906360455");
        requestDTO.setName("John");
        requestDTO.setPhoneNumber(4564L);
        requestDTO.setCompanyName("qwer");
        requestDTO.setCnpj("01222244");
        requestDTO.setPlanType(PlanType.PRE);

        ClientResponseDTO responseDTO = clientService.createClient(requestDTO);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getCpf()).isEqualTo("60906360455");

        Client client = clientRepository.findById(responseDTO.getId()).orElse(null);
        assertThat(client).isNotNull();
        assertThat(client.getCpf()).isEqualTo("60906360455");

        Optional<ClientPlan> clientPlan = clientPlanRepository.findClientPlanByClientIdAndPlanId(client.getId(), planRepository.findByType(PlanType.PRE).get().getId());

        assertThat(clientPlan)
                .isNotEmpty()
                .hasValueSatisfying(cp -> {
                    assertThat(cp.getId()).isNotNull();
                    assertThat(cp.getAccount()).isNotNull();
                });

        assertThat(clientPlan.get().getAccount().getBalance()).isEqualTo(100.0);

        Optional<ClientPlan> nonExistentClientPlan = clientPlanRepository.findClientPlanByClientIdAndPlanId(client.getId(), planRepository.findByType(PlanType.POS).get().getId());
        assertThat(nonExistentClientPlan).isEmpty();
    }
}