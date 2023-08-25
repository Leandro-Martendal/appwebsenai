package com.example.appwebsenai.controller;

import com.example.appwebsenai.model.Conta;
import com.example.appwebsenai.model.ContaCorrentePF;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BancoRepository extends CrudRepository<ContaCorrentePF, Long> {
    @Query("SELECT c FROM ContaCorrentePF c ORDER BY c.numeroConta DESC")
    List<ContaCorrentePF> findTopByOrderByIdDesc();
}