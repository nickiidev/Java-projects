package Empresa;

import java.util.Scanner;

public class Utils {

    public static void exibirMenu() {

        System.out.println("=============================");
        System.out.println("    Sistema Empresarial");
        System.out.println("=============================");
        System.out.println("1 - Cadastrar Funcionario CLT");
        System.out.println("2 - Cadastrar Funcionario PJ");
        System.out.println("3 - Cadastrar Gerente");
        System.out.println("4 - Listar Funcionarios");
        System.out.println("5 - Calcular Folha Salarial");
        System.out.println("6 - Realizar Login de Gerente");
        System.out.println("7 - Buscar Funcionario por CPF");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void cadastrarFuncionario(Scanner sc, Services services, int opcao) {
    System.out.print("Digite o Nome: ");
	String nome = sc.nextLine();

	System.out.print("Digite o CPF: ");
	String cpf = sc.nextLine();


	System.out.print("Digite o Salario Base: ");
	double salarioBase = sc.nextDouble();
	sc.nextLine();

        switch (opcao) {
            case 1:
                services.adicionarFuncionario(new FuncionarioCLT(cpf, nome, salarioBase, "CLT"));
                System.out.println("Funcionario CLT cadastrado com sucesso!");
                break;

            case 2:
                services.adicionarFuncionario(new FuncionarioPJ(cpf, nome, salarioBase, "PJ"));
                System.out.println("Funcionario PJ cadastrado com sucesso!");
                break;

            case 3:
	 			System.out.print("Digite a senha: ");
        		String senha = sc.nextLine();
                services.adicionarFuncionario(new Gerente(cpf, nome, salarioBase, senha, "Gerente"));
                System.out.println("Gerente cadastrado com sucesso!");
                break;
        }
	}

	public void login(Scanner sc, Services services){

		System.out.println("--- LOGIN DE GERENTE ---");
		System.out.print("digite o CPF do gerente: ");
		String cpfLogin = sc.nextLine();
		System.out.print("digite a senha: ");
		String senhaLogin = sc.nextLine();

		services.loginGerente(cpfLogin, senhaLogin);

	}

	public void busca(Scanner sc, Services services) {

		System.out.println("--- BUSCA DE FUNCIONARIO POR CPF ---");
		System.out.print("Digite o CPF do Funcionario: ");
		String cpfBusca = sc.nextLine();
		services.buscarFuncionario(cpfBusca);
	}

}