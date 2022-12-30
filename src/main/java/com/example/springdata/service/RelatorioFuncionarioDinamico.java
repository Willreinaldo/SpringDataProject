package com.example.springdata.service;

import com.example.springdata.Specification.SpecificationFuncionario;
import com.example.springdata.orm.Funcionario;
import com.example.springdata.repository.FuncionarioRepository;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Scanner;

public class RelatorioFuncionarioDinamico {

    private final FuncionarioRepository funcionarioRepository;

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository){
    this.funcionarioRepository = funcionarioRepository;
    }
    public void inicial(Scanner scanner){
        System.out.println("Digite um nome");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification.
                where(SpecificationFuncionario.nome(nome)));
    }
}
