package com.smsender.utils;

import com.smsender.entity.*;
import com.smsender.enums.PlanType;
import com.smsender.enums.StatusType;
import com.smsender.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Initialzer implements ApplicationRunner {

    private ClientRepository clientRepository;
    private PlanRepository planRepository;
    private ClientPlanRepository clientPlanRepository;
    private TransactionRepository transactionRepository;
    private MessageRepository messageRepository;

    public Initialzer(ClientRepository clientRepository, PlanRepository planRepository, ClientPlanRepository clientPlanRepository, TransactionRepository transactionRepository,MessageRepository messageRepository) {
        this.clientRepository = clientRepository;
        this.planRepository = planRepository;
        this.clientPlanRepository = clientPlanRepository;
        this.transactionRepository = transactionRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Client client1 = new Client();
        client1.setName("Client 1");
        client1.setPhoneNumber(12123);
        client1.setCompanyName("test1");
        client1.setCpf("23456");
        client1.setCnpj("112345");
        client1 = clientRepository.save(client1);

        Client client2 = new Client();
        client2.setName("Client 2");
        client2.setPhoneNumber(1212312);
        client2.setCompanyName("test2");
        client2.setCpf("23456545");
        client2.setCnpj("11234511");
        client2 = clientRepository.save(client2);

        // Criar e salvar planos
        Plan plan1 = new Plan();
        plan1.setType(PlanType.POSPAGO);
        plan1 = planRepository.save(plan1);

        Plan plan2 = new Plan();
        plan2.setType(PlanType.PREPAGO);
        plan2 = planRepository.save(plan2);

        // Criar e salvar associações entre cliente e plano
        ClientPlan clientPlan1 = new ClientPlan();
        ClientPlanId id1 = new ClientPlanId(client1, plan1);
        AccountPos account1 = new AccountPos();
        account1.setId(1L);
        account1.setAccountNumber(123456L);
        account1.setMaximumValue(122L);
        clientPlan1.setId(id1);
        clientPlan1.setAccount(account1);
        clientPlanRepository.save(clientPlan1);

        ClientPlan clientPlan2 = new ClientPlan();
        ClientPlanId id2 = new ClientPlanId(client2, plan2);
        clientPlan2.setId(id2);
        AccountPre account2 = new AccountPre();
        account2.setId(1L);
        account2.setAccountNumber(123456L);
        account2.setCreditValue(122L);
        clientPlan2.setAccount(account2);
        clientPlanRepository.save(clientPlan2);


        Message message = new Message();
        message.setStatus(StatusType.S);
        message.setContent("euheuheu");
        message.setRecipientPhoneNumber(12345);
        messageRepository.save(message);

        // Criar e salvar transações (se aplicável)
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAccount(account1);
        transaction.setMessage(message);
        transaction.setMessageCost(25L);
        transactionRepository.save(transaction);
    }

}
