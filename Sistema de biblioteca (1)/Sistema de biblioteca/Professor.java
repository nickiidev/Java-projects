public class Professor extends Usuario {
    private String disciplina;

    public Professor(Integer id, String nome, String email, String disciplina) {
        super(id, nome, email, 0);
        this.disciplina = disciplina;
    }
    
    public String getDisciplina() { return disciplina; }
    
    // Regra de negócio: professor pode pegar 5 livros.
    public int getLimiteLivros() { return 5; }
    
    public String toString() { 
        return super.toString() + " | Tipo: Professor | Disciplina: " + disciplina; 
    }
}