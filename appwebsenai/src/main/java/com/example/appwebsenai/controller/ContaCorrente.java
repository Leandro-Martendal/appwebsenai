package com.example.appwebsenai.controller;

import com.example.appwebsenai.model.ContaCorrentePF;

public interface ContaCorrente {

    Double sacar(Double quantidade, String name);

    String depositar(Double quantidade, String name);

    String transferir(Double quantidade, Long contaOrigem, Long contaDestino);

    Double consultaSaldo(ContaCorrentePF conta);

    Double extrato(Long contaOrigem);

}