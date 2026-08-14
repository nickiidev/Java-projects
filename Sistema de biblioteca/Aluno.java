public class Aluno extends Usuario {
    private String matricula;

    public Aluno(Integer id, String nome, String email, String matricula) {
        super(id, nome, email, 0); // O 0 é a quantidade inicial de empréstimos.
        this.matricula = matricula;
    }
    
    public String getMatricula() { return matricula; }
    
    // Regra de negócio: aluno só pode pegar 3 livros.
    public int getLimiteLivros() { return 3; }
    
    public String toString() { 
        return super.toString() + " | Tipo: Aluno | Matricula: " + matricula; 
    }
}