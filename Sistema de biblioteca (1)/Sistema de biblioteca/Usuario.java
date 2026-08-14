// Classe abstrata, serve de molde para Aluno e Professor.
public abstract class Usuario {
    private Integer id;
    private String nome;
    private String email;
    private int quantidadeEmprestimos;

    public Usuario(Integer id, String nome, String email, int quantidadeEmprestimos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.quantidadeEmprestimos = quantidadeEmprestimos;
    }

    public Integer getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public int getQuantidadeEmprestimos() { return quantidadeEmprestimos; }
    
    // Métodos para gerenciar o limite de livros na memória antes de ir pro banco
    public void setQuantidadeEmprestimos(int qtd) { this.quantidadeEmprestimos = qtd; }
    public void adicionarEmprestimo() { this.quantidadeEmprestimos++; }
    public void removerEmprestimo() { this.quantidadeEmprestimos--; }

    // Método abstrato que força Aluno e Professor a terem seus próprios limites.
    public abstract int getLimiteLivros();

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Email: " + email;
    }
}