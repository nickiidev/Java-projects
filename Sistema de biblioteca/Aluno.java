public class Aluno extends Usuario {

    private String matricula;

    public Aluno(Integer id, String nome, String email, String matricula) {
        super(id, nome, email, 0);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;   
    }

    public int getLimiteLivros() {
        return 3;
    }

    public String toString() {
        return super.toString() + " | Matricula: " + matricula;
    }
}