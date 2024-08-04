package com.smsender.builder;

import com.smsender.entity.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClientBuilder {
    private String name;
    private String cpf;
    private Long phoneNumber;
    private String cnpj;
    private String companyName;

    public ClientBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ClientBuilder phoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ClientBuilder cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public ClientBuilder companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public Client build() {
        return new Client(this);
    }
}
