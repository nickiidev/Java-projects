public class Livro {

    private Integer id; 
    private String titulo;
    private boolean disponivel; 

    public Livro(Integer id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.disponivel = true; 
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() { 
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String toString() {
        String status = disponivel ? "Sim" : "Não";
        return "ID do livro: " + id + " | Titulo: " + titulo + " | Disponivel: " + status;
    }
}