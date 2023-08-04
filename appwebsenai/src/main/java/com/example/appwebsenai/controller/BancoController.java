package com.example.appwebsenai.controller;

import com.example.appwebsenai.model.AccountType;
import com.example.appwebsenai.model.ContaCorrentePF;
import com.example.appwebsenai.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoController implements ContaCorrente{

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private Controller controller;

    private Long number = 0L;
    StringBuilder message = new StringBuilder();

    @Override
    public Double consultaSaldo(ContaCorrentePF conta) {
        return null;
    }

    public ContaCorrentePF criarConta(String name, String accountType) throws Exception {
        message.setLength(0);
        ContaCorrentePF contaCorrentePF = new ContaCorrentePF();
        if(accountType == null){
            message.append("\nNecessário informar o tipo da conta!");
        }
        switch (accountType){
            case "POUPANCA" :
                contaCorrentePF.setAccountType(AccountType.CONTA_POUPANCA);
                break;
            case "CORRENTE" :
                contaCorrentePF.setAccountType(AccountType.CONTA_CORRENTE);
            default:
                message.append("\nTipo da conta não é suportado!");
        }

        Person person = controller.findPerson(name);
        if(person != null && contaCorrentePF.getError() == null){
            number++;
            contaCorrentePF.setNumeroConta(number);
            contaCorrentePF.setPerson(person);
            bancoRepository.save(contaCorrentePF);
        }else if(contaCorrentePF.getError() == null){
            message.append("\nPessoa ");
            message.append(name).append(" informada não foi cadastrada");
        }
        if(!message.isEmpty()){
            contaCorrentePF.setError(message.toString());
        }

        return contaCorrentePF;
    }

    public ContaCorrentePF consultaConta(String name){

        List<ContaCorrentePF> contas = (List<ContaCorrentePF>) bancoRepository.findAll();

        for(ContaCorrentePF cc : contas){
            if(cc.getPerson() != null && cc.getPerson().getName().equals(name)){
                return cc;
            }
        }
        return null;
    }

    public String depositar(Double quantidade, String name) {
        message.setLength(0);
        consultaConta(name);
        ContaCorrentePF n2 = consultaConta(name);
        if(n2.getSaldo() != null) {
            Double total = n2.getSaldo() + quantidade;
            n2.setSaldo(total);
            message.append("\nVocê depositou R$").append(quantidade).append(", em sua conta");
            message.append("\nVocê possui R$").append(total).append(", em sua conta");
            } else{
            n2.setSaldo(quantidade);
            Double total = quantidade;
            message.append("\nVocê depositou R$").append(quantidade).append(", em sua conta");
            message.append("\nVocê possui R$").append(total).append(", em sua conta");
            }
        bancoRepository.save(n2);
        return String.valueOf(message);
    }

    @Override
    public String transferir(Double quantidade, Long contaOrigem, Long contaDestino) {
        message.setLength(0);
        ContaCorrentePF n3 = bancoRepository.findById(contaOrigem).get();
        ContaCorrentePF n4 = bancoRepository.findById(contaDestino).get();

        if (n3 != null && n4 != null) {
            if (n3.getSaldo() >= quantidade) {
                Double totalOrigem = n3.getSaldo() - quantidade;
                Double totalDestino = (n4.getSaldo() != null ? n4.getSaldo() : 0.0) + quantidade;
                n3.setSaldo(totalOrigem);
                n4.setSaldo(totalDestino);
                bancoRepository.save(n3);
                bancoRepository.save(n4);
                message.append("\nVocê transferiu R$").append(quantidade).append(", para ").append(n4.getPerson().getName());
            } else {
                message.append("\nSaldo insuficiente para a operação.");
            }
        } else {
            message.append("\nConta(s) não encontrada(s).");
        }
        return String.valueOf(message);
    }

    @Override
    public Double sacar(Double quantidade, String name) {
        message.setLength(0);
        ContaCorrentePF n5 = consultaConta(name);
        Double saldoRestante = null;
        if (n5 != null) {
            saldoRestante = n5.getSaldo();

            if (n5.getSaldo() >= quantidade) {
                Double total = n5.getSaldo() - quantidade;
                n5.setSaldo(total);
                bancoRepository.save(n5);
                saldoRestante = total;
            } else {
                message.append("\nSaldo insuficiente para o saque.");
                saldoRestante = n5.getSaldo(); // Saldo restante é igual ao saldo atual, pois não foi possível sacar o valor total desejado.
            }
        } else {
            message.append("\nConta não encontrada.");
        }
        message.append("\nVocê sacou R$").append(quantidade).append(", muito obrigado por utilizar nossos serviços ");
        return saldoRestante;
    }

    @Override
    public Double extrato(Long contaOrigem){
        message.setLength(0);
        ContaCorrentePF n3 = bancoRepository.findById(contaOrigem).get();
        return n3.getSaldo();
    }

}