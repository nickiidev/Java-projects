package Empresa;

import java.util.ArrayList;

public class Services {

	private ArrayList<Funcionario> funcionarios = new ArrayList<>();

	public void adicionarFuncionario(Funcionario f) {
		funcionarios.add(f);
	}


	public void cadastrarFuncionario(String nome) {
		System.out.print("Nome: " + nome);

	}

	public void cadastrarFuncionario(String nome, String cpf) {
		System.out.print("Nome: " + nome + ", CPF: " + cpf);

	}

	public void cadastrarFuncionario(String nome, String cpf, double salarioBase) {
		System.out.print("Nome: " + nome + ", CPF: " + cpf + ", Salário Base: " + salarioBase);

	}

	public void listarFuncionarios() {
		
		if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return; 
        }

		for (Funcionario f : funcionarios) {

			System.out.println(f.toString());
			if (f instanceof Bonificacao) {
				Bonificacao b = (Bonificacao) f;
			System.out.println("Bonus: " + b.calcularBonus());
			}
			System.out.println("---------------------------");
		}

	}

	public void calcularFolhaSalarial() {

		System.out.println("--- CALCULO DA FOLHA SALARIAL ---");

		double total = 0;
		for (Funcionario f : funcionarios) {
			total += f.calcularSalario();
		}

		System.out.println("Folha Salarial total da empresa: " + total);
	}

	public boolean loginGerente(String cpfLogin, String senhaLogin) {

		for (Funcionario f : funcionarios) {

			if (f instanceof Gerente) {

			    Gerente g = (Gerente) f;

				if (g.getCpf().equals(cpfLogin)) {

					if (g.login(senhaLogin)) {
						System.out.println("Login realizado com sucesso!");
						return true;
					} else {
						System.out.println("Senha incorreta!");
						return false;
					}

				} else {
					System.out.println("CPF do gerente nao encontrado!");
					return false;
				}
			}
		}	
		return false;
	} 
	

	public void buscarFuncionario(String cpfBusca) {

		for (Funcionario f : funcionarios) {

			if (f.getCpf().equals(cpfBusca)) {
				System.out.println("Funcionario encontrado: ");
				System.out.println("=============================");
				listarFuncionarios();
				return;
			
			} else {
			System.out.println("Funcionario não encontrado!");
			}
		}
	}
}