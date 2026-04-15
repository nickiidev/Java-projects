public class Emprestimo {

    private Integer id;
    private String dataEmprestimo;
    private String dataDevolucao;
    private Usuario usuario;
    private Livro livro;

    public Emprestimo(Integer id, String dataEmprestimo, String dataDevolucao, Usuario usuario, Livro livro) {

        this.id = id;
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Integer getId() { 
        return id; 
    }

    public String getDataEmprestimo() { 
        return dataEmprestimo; 
    }

    public String getDataDevolucao() { 
        return dataDevolucao; 
    }

    public Usuario getUsuario() { 
        return usuario; 
    }

    public Livro getLivro() { 
        return livro; 
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String toString() {
        return "\nID do Emprestimo: " + id + "\nData Emprestimo: " + dataEmprestimo + "\nData Devolucao: " + dataDevolucao;
    }
    
}