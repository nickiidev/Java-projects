public class Livro {
    private Integer id;
    private String titulo;
    private boolean disponivel;

    public Livro(Integer id, String titulo, boolean disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.disponivel = disponivel;
    }

    public Integer getId() { return id; }
    public String getTitulo() { return titulo; }
    public boolean isDisponivel() { return disponivel; }
    
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public String toString() {
        // Converte o boolean para Sim ou Não no console
        String status = disponivel ? "Sim" : "Não";
        return "ID: " + id + " | Titulo: " + titulo + " | Disponivel: " + status;
    }
}