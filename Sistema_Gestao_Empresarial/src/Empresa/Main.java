package Empresa;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		Services services = new Services();
		Utils utils = new Utils();

        do {
			Utils.exibirMenu();
            
            int opcao = sc.nextInt();
            sc.nextLine();
            
            switch (opcao) {
            	case 1:

            		System.out.println("--- CADASTRO DE FUNCIONARIO CLT ---");
					utils.cadastrarFuncionario(sc, services, opcao);
            		break;
            		
            	case 2:

					System.out.println("--- CADASTRO DE FUNCIONARIO PJ ---");
					utils.cadastrarFuncionario(sc, services, opcao);
            		break;

            	case 3:

					System.out.println("--- CADASTRO DE GERENTE ---");
					utils.cadastrarFuncionario(sc, services, opcao);
            		break;
					
            	case 4:

					System.out.println("=============================");
					System.out.println("   LISTA DE FUNCIONARIOS");
					System.out.println("=============================");

					services.listarFuncionarios();
            		break;

            	case 5:
					services.calcularFolhaSalarial();
            		break;

            	case 6:
					utils.login(sc, services);
            		break;

            	case 7:
					utils.busca(sc, services);
            		break;

            	case 0:
					System.out.println("Encerrando o sistema...");
					sc.close();
            		break;
            		
            	default:
            		System.out.println("Opcao invalida!");
					break;
            }

		} while (true);
	}

}
