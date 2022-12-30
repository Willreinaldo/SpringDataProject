package com.example.springdata.service;

import com.example.springdata.orm.Funcionario;
import com.example.springdata.orm.FuncionarioProjecao;
import com.example.springdata.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de busca execultar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionário por nome");
            System.out.println("2 - Busca funcionário por nome, data contratação e salário maior");
            System.out.println("3 - Busca funcionário por data de contratação");
            System.out.println("4 - Busca por funcionário - salário");


            int action = scanner.nextInt();
            switch(action) {
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscarFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacao(scanner);
                    break;
                case 4:
                    buscaFuncionarioSalario();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaFuncionarioNome(Scanner scanner){
        System.out.println("Digite o nome do funcionário");
        String nome = scanner.next();
        List<Funcionario> list = funcionarioRepository.findByNome(nome);
        list.forEach(System.out::println);
    }

    private void buscarFuncionarioNomeSalarioMaiorData(Scanner scanner){
        System.out.println("Qual nome deseja pesquisar");
        String nome = scanner.next();
        System.out.println("Qual data de contratação deseja pesquisar?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);
        System.out.println("Qual salário deseja pesquisar?");
        Double salario = scanner.nextDouble();

        List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome,salario,localDate);
            list.forEach(System.out::println);
    }
    private void buscaFuncionarioDataContratacao(Scanner scanner){
        System.out.println("Qual data de contratação deseja pesquisar?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);
        List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
        list.forEach(System.out::println);

    }

    private void buscaFuncionarioSalario(){
        List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
        list.forEach(f -> System.out.println("Funcionario id: "+ f.getId()
                + " | nome" + f.getNome() + "| Salario: " + f.getSalario()));
    }
}
