import java.sql.*;
import java.util.List;

public class Biblioteca {
    // Usando as interfaces para conversar com o banco
    private LivroRepository livroRepo = new LivroRepositorySQLite();
    private UsuarioRepository usuarioRepo = new UsuarioRepositorySQLite();

    // Repassa a ordem de salvar para o repositório
    public void adicionarLivro(Livro l) {
        livroRepo.salvar(l);
    }

    public void adicionarUsuario(Usuario u) {
        usuarioRepo.salvar(u);
    }

    public void emprestarLivro(int idLivro, int idUsuario, String dataEmprestimo) {
        // Busca do banco pra ver se existem
        Livro l = livroRepo.buscarPorId(idLivro);
        Usuario u = usuarioRepo.buscarPorId(idUsuario);

        if (l != null && u != null) {
            if (l.isDisponivel()) { // Verifica se tá livre
                if (u.getQuantidadeEmprestimos() < u.getLimiteLivros()) { // Verifica a regra de negócio
                    
                    // Altera a disponibilidade e atualiza no banco
                    livroRepo.atualizarDisponibilidade(l.getId(), false);
                    u.adicionarEmprestimo();
                    usuarioRepo.atualizarQuantidadeEmprestimos(u.getId(), u.getQuantidadeEmprestimos());

                    // Insere o registro de empréstimo no banco
                    String sql = "INSERT INTO emprestimos(id_usuario, id_livro, data_emprestimo, status) VALUES(?,?,?, 'Pendente')";
                    try (PreparedStatement pstmt = DatabaseConnection.getInstance().prepareStatement(sql)) {
                        pstmt.setInt(1, u.getId());
                        pstmt.setInt(2, l.getId());
                        pstmt.setString(3, dataEmprestimo);
                        pstmt.executeUpdate();
                        System.out.println("Livro alugado com sucesso!");
                    } catch (SQLException e) {
                        System.out.println("Erro ao registrar empréstimo.");
                    }
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
        // Busca qual empréstimo está com esse livro
        String sqlBusca = "SELECT * FROM emprestimos WHERE id_livro = ? AND status = 'Pendente'";
        try (PreparedStatement pstmt = DatabaseConnection.getInstance().prepareStatement(sqlBusca)) {
            pstmt.setInt(1, idLivro);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                int idEmprestimo = rs.getInt("id");

                // Libera o livro no banco
                livroRepo.atualizarDisponibilidade(idLivro, true);
                
                // Tira um da cota do usuário e atualiza no banco
                Usuario u = usuarioRepo.buscarPorId(idUsuario);
                u.removerEmprestimo();
                usuarioRepo.atualizarQuantidadeEmprestimos(u.getId(), u.getQuantidadeEmprestimos());

                // Muda o status do aluguel para Devolvido
                String sqlUpdate = "UPDATE emprestimos SET status = 'Devolvido' WHERE id = ?";
                try (PreparedStatement pUp = DatabaseConnection.getInstance().prepareStatement(sqlUpdate)) {
                    pUp.setInt(1, idEmprestimo);
                    pUp.executeUpdate();
                }
                System.out.println("Livro devolvido!");
            } else {
                System.out.println("Aluguel ativo não encontrado para este livro.");
            }
        } catch (SQLException e) {}
    }

    public void listarLivros() {
        System.out.println("============= Catálogo de Livros =============");
        List<Livro> livros = livroRepo.listarTodos(); // Busca a lista toda do banco
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (Livro l : livros) {
                System.out.println(l);
            }
        }
    }

    public void listarEmprestimos() {
        System.out.println("============== Emprestimos Ativos ==============");
        String sql = "SELECT * FROM emprestimos WHERE status = 'Pendente'";
        try (Statement stmt = DatabaseConnection.getInstance().createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            boolean tem = false;
            while (rs.next()) {
                tem = true;
                // Exibe os dados que vieram do banco
                System.out.println("Emprestimo ID: " + rs.getInt("id") + " | Livro ID: " + rs.getInt("id_livro") + " | Usuario ID: " + rs.getInt("id_usuario") + " | Data: " + rs.getString("data_emprestimo"));
            }
            if (!tem) System.out.println("Nenhum emprestimo pendente.");
        } catch (SQLException e) {}
    }

    public void listarUsuarios() {
        System.out.println("============= Lista de Usuarios =============");
        String sql = "SELECT * FROM usuarios";
        try (Statement stmt = DatabaseConnection.getInstance().createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            boolean tem = false;
            while (rs.next()) {
                tem = true;
                // Exibe os dados que vieram do banco
                System.out.println("Usuario ID: " + rs.getInt("id") + " | Nome: " + rs.getString("nome") + " | Email: " + rs.getString("email") + " | Tipo: " + rs.getString("tipo") + " | Quantidade de Emprestimos: " + rs.getInt("quantidade_emprestimos"));
            }
            if (!tem) System.out.println("Nenhum usuario cadastrado.");
        } catch (SQLException e) {}
    }
}