package com.smsender.entity;

import com.smsender.builder.ClientBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CLIENT")
@NoArgsConstructor
public class Client extends BaseEntity {

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private Integer phoneNumber;

    @Column(length = 14, nullable = false)
    private String cnpj;

    @Column(length = 50, nullable = false)
    private String companyName;

    public Client(ClientBuilder builder) {
        this.name = builder.getName();
        this.cpf = builder.getCpf();
        this.phoneNumber = builder.getPhoneNumber();
        this.cnpj = builder.getCnpj();
        this.companyName = builder.getCompanyName();
    }

    public static ClientBuilder builder() {
        return new ClientBuilder();
    }
}
