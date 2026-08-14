package Empresa;

public class FuncionarioCLT extends Funcionario implements Bonificacao {
	
	public FuncionarioCLT(String cpf, String nome, double salarioBase, String tipo) {
		super(cpf, nome, salarioBase, tipo);
		
	}
	
	public String toString() {
	    return super.toString();
	}
	
	@Override
	public double calcularSalario() {
		return getSalarioBase() * 1.10;
	}

	@Override
	public double calcularBonus() {
		return getSalarioBase() * 0.05;

	}

	@Override
	public String getTipo() {
		return "CLT";
	}
	
}
