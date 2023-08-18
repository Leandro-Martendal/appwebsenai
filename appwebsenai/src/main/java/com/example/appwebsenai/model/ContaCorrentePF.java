package com.example.appwebsenai.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "conta")
public class ContaCorrentePF{

    @Id
    @Column(name = "numero_conta")
    private Long numeroConta;

    private Double saldo;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "Type")
    private AccountType AccountType;

    @Column(name= "criacao")
    private Date dataAtualizacao;

    @Transient
    private String error;


    public com.example.appwebsenai.model.AccountType getAccountType() {
        return AccountType;
    }

    public void setAccountType(com.example.appwebsenai.model.AccountType accountType) {
        AccountType = accountType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}