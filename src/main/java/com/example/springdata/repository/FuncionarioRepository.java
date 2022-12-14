package com.example.springdata.repository;


import com.example.springdata.orm.Funcionario;
import com.example.springdata.orm.FuncionarioProjecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>,
        JpaSpecificationExecutor<Funcionario> {

    List<Funcionario> findByNome(String nome);
    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario" +
            " AND f.dataContratacao = :localDate")
    List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, double salario, LocalDate localDate);

    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);

    @Query(value="SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();
}
