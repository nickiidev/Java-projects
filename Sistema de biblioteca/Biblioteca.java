import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();


    public void adicionarLivro(Livro l) {
        livros.add(l);
    }

    public void adicionarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public Livro buscarLivro(int id) {
        for (Livro l : livros) {    
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    public Usuario buscarUsuario(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public void emprestarLivro(int idLivro, int idUsuario, String dataEmprestimo) {
        Livro l = buscarLivro(idLivro);
        Usuario u = buscarUsuario(idUsuario);

        if (l != null && u != null) {
            if (l.isDisponivel()) {
                if (u.getQuantidadeEmprestimos() < u.getLimiteLivros()) {
                    l.setDisponivel(false);
                    u.adicionarEmprestimo();
                    Emprestimo e = new Emprestimo(emprestimos.size() + 1, dataEmprestimo, "Pendente", u, l);
                    emprestimos.add(e);
                    System.out.println("Livro alugado com sucesso!");
                } else {
                    System.out.println("Usuário atingiu o limite de empréstimos.");
                }
            } else {
                System.out.println("Livro indisponível.");
            }
        } else {
            System.out.println("Erro: Usuário ou livro não encontrado.");
        }
    }

    public void devolverLivro(int idLivro) {
        for (Emprestimo e : emprestimos) {
            if (e.getLivro().getId() == idLivro) {
                e.getLivro().setDisponivel(true);
                e.getUsuario().removerEmprestimo();
                emprestimos.remove(e);
                System.out.println("Livro devolvido!");
                return;
            }
        }
        System.out.println("Aluguel não encontrado.");
    }

    public void listarLivros() {
        System.out.println("\n  ============= Catálogo de Livros =============\n");

        if (livros.isEmpty()) {
                System.out.println("Nenhum livro cadastrado.");
                return; 
            }

        for (Livro l : livros) {
            System.out.println(l);
            
        }
    }

    public void listarEmprestimos() {
        System.out.println("\n  ============== Emprestimos ==============\n");

        if (emprestimos.isEmpty()) {
                System.out.println("Nenhum emprestimo registrado.");
                return;
            }

        for (Emprestimo e : emprestimos) {
            System.out.println(e);
        }
    }
}