package Empresa;

public abstract class Funcionario {
	
	 private String cpf;
	 private String nome;
	 private double salarioBase;
	 private String tipo;
	 
	 
	public Funcionario(String cpf, String nome, double salarioBase, String tipo) {
	    this.cpf = cpf;
	    this.nome = nome;
	    this.salarioBase = salarioBase;
	    this.tipo = tipo;
	}
	 
	public String getCpf() {
		return cpf;	
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getSalarioBase() {
		return salarioBase;
	}
	
	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public String getTipo() {
		return tipo;
	}

	public abstract double calcularSalario();
	
	public String toString() {

        return "Nome: " + nome + "\n" +
               "CPF: " + cpf + "\n" +
			   "Tipo: " + tipo + "\n" +
               "Salário Final: " + calcularSalario();

    }

}
