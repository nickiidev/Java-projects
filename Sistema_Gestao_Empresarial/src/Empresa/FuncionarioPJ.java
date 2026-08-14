package Empresa;

public class FuncionarioPJ extends Funcionario {
	
	public FuncionarioPJ(String cpf, String nome, double salarioBase, String tipo) {
		super(cpf, nome, salarioBase, tipo);
	}

	@Override
	public double calcularSalario() {
		return getSalarioBase();
	}
	
	public String toString() {
	    return super.toString();
	}

	@Override
	public String getTipo() {
		return "PJ";
	}

}
