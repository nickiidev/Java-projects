 public abstract class Usuario {

    private Integer id;
    private String nome;
    private String email;
    private int quantidadeEmprestimos;

    public Usuario(Integer id, String nome, String email, int quantidadeEmprestimos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.quantidadeEmprestimos = 0;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    } 

    public int getQuantidadeEmprestimos() {
        return quantidadeEmprestimos;
    }

    public void adicionarEmprestimo() {
        this.quantidadeEmprestimos++;
    }

    public void removerEmprestimo() {
        this.quantidadeEmprestimos--;
    }

    public abstract int getLimiteLivros();

    @Override
    public String toString() {
        return "ID do Usuário: " + id + " | Nome: " + nome + " | Email: " + email;
    }

}