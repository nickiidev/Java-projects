package Empresa;

public class Gerente extends Funcionario implements Bonificacao, Autenticavel {

	private String senha;
	
	public Gerente(String cpf, String nome, double salarioBase, String senha, String tipo) {
		super(cpf, nome, salarioBase, tipo);
		this.senha = senha;
	}
	
	public String toString() {
	    return super.toString();
	}
	
	@Override
	public double calcularSalario() {
		return getSalarioBase() * 1.30;
	
	}

	@Override
	public double calcularBonus() {
		return getSalarioBase() * 0.20;
	
	}

	@Override
	public Boolean login(String senha) {
		return this.senha.equals(senha);
	}

	@Override
	public String getTipo() {
		return "Gerente";
	}

}
