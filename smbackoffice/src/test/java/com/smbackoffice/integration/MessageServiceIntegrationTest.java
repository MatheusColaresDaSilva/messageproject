package com.smbackoffice.integration;

import com.smbackoffice.dto.request.DeductionRequestDTO;
import com.smbackoffice.entity.AccountPre;
import com.smbackoffice.entity.Message;
import com.smbackoffice.entity.Transaction;
import com.smbackoffice.enums.StatusType;
import com.smbackoffice.exception.AccountNotFoundException;
import com.smbackoffice.exception.MessageNotFoundException;
import com.smbackoffice.repository.AccountRepository;
import com.smbackoffice.repository.MessageRepository;
import com.smbackoffice.repository.TransactionRepository;
import com.smbackoffice.service.RequestDeductionAcconuntClientService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class MessageServiceIntegrationTest {

    @Autowired
    private RequestDeductionAcconuntClientService requestDeductionAcconuntClientService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private static Long accountId;
    private static Long messageId;

    @BeforeAll
    public static void setupData(@Autowired AccountRepository accountRepo,
                                 @Autowired MessageRepository messageRepo) {
        AccountPre account = new AccountPre();
        account.setAccountNumber(123456L);
        account.setCreditValue(100.0);
        account = accountRepo.save(account);
        accountId = account.getId();

        Message message = new Message();
        message.setContent("THIS IS A TEST");
        message.setRecipientPhoneNumber(12345);
        message.setIsWhats(false);
        message = messageRepo.save(message);
        messageId = message.getId();
    }

    @Test
    public void testDeductionAccountWithSuccess() {


        DeductionRequestDTO requestDTO = new DeductionRequestDTO();
        requestDTO.setIdMessage(accountId);
        requestDTO.setIdAccount(messageId);

        requestDeductionAcconuntClientService.deductionAccount(requestDTO);

        Message updatedMessage = messageRepository.findById(1L).orElseThrow();
        assertEquals(StatusType.S, updatedMessage.getStatus());

        Optional<Transaction> transactionOp = transactionRepository.findByAccountIdAndMessageId(1L, 1L);
        assertTrue(transactionOp.isPresent());
    }

    @Test
    public void testDeductionAccountWithMessageNotFound() {

        DeductionRequestDTO requestDTO = new DeductionRequestDTO();
        requestDTO.setIdMessage(999L); // A unexistent id
        requestDTO.setIdAccount(messageId);

        Exception exception = assertThrows(MessageNotFoundException.class, () -> {
            requestDeductionAcconuntClientService.deductionAccount(requestDTO);
        });

    }

    @Test
    public void testDeductionAccountWithAccountNotFound() {

        DeductionRequestDTO requestDTO = new DeductionRequestDTO();
        requestDTO.setIdMessage(accountId);
        requestDTO.setIdAccount(999L); // A unexistent id

        Exception exception = assertThrows(AccountNotFoundException.class, () -> {
            requestDeductionAcconuntClientService.deductionAccount(requestDTO);
        });

    }

}
