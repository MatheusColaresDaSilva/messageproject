package com.smbackoffice.service;


import com.smbackoffice.entity.AccountPos;
import com.smbackoffice.entity.AccountPre;
import com.smbackoffice.repository.AccountRepository;
import com.smbackoffice.repository.MessageRepository;
import com.smbackoffice.entity.Message;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//@Component
public class Initialzer implements ApplicationRunner {

    private AccountRepository accountRepository;
    private MessageRepository messageRepository;

    public Initialzer(AccountRepository accountRepository, MessageRepository messageRepository) {
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


        AccountPos account1 = new AccountPos();
        account1.setId(1L);
        account1.setAccountNumber(123456L);
        account1.setMaximumValue(27.0);

        accountRepository.save(account1);

        Message message = new Message();
        message.setContent("euheuheu");
        message.setRecipientPhoneNumber(12345);
        messageRepository.save(message);

        AccountPre account2 = new AccountPre();
        account2.setId(1L);
        account2.setAccountNumber(123456L);
        account2.setCreditValue(26.0);
        accountRepository.save(account2);

    }

}
