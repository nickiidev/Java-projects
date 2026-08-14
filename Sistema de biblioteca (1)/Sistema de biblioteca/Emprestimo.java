public class Emprestimo {
    private Integer id;
    private String dataEmprestimo;
    private String status;
    private int idUsuario;
    private int idLivro;

    public Emprestimo(Integer id, int idUsuario, int idLivro, String dataEmprestimo, String status) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLivro = idLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.status = status;
    }

    public String toString() {
        return "Emprestimo ID: " + id + " | ID Livro: " + idLivro + " | ID Usuario: " + idUsuario + " | Data: " + dataEmprestimo + " | Status: " + status;
    }
}