import java.sql.*;

public class UsuarioRepositorySQLite implements UsuarioRepository {
    private Connection conn;

    public UsuarioRepositorySQLite() {
        this.conn = DatabaseConnection.getInstance();
    }

    @Override
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios(id, nome, email, tipo, matricula, disciplina, quantidade_emprestimos) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuario.getId());
            pstmt.setString(2, usuario.getNome());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setInt(7, usuario.getQuantidadeEmprestimos());
            
            // Verifica qual é a classe filha para gravar os dados específicos
            if (usuario instanceof Aluno) {
                pstmt.setString(4, "ALUNO");
                pstmt.setString(5, ((Aluno) usuario).getMatricula());
                pstmt.setString(6, null);
            } else if (usuario instanceof Professor) {
                pstmt.setString(4, "PROFESSOR");
                pstmt.setString(5, null);
                pstmt.setString(6, ((Professor) usuario).getDisciplina());
            }
            pstmt.executeUpdate(); // Executa o salvamento no banco
        } catch (SQLException e) {
            System.out.println("Erro ao salvar usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String tipo = rs.getString("tipo");
                Usuario u;
                // Lê do banco e reconstrói o objeto certo
                if (tipo.equals("ALUNO")) {
                    u = new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("matricula"));
                } else {
                    u = new Professor(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("disciplina"));
                }
                u.setQuantidadeEmprestimos(rs.getInt("quantidade_emprestimos"));
                return u;
            }
        } catch (SQLException e) {}
        return null;
    }

    @Override
    public void atualizarQuantidadeEmprestimos(int id, int novaQuantidade) {
        String sql = "UPDATE usuarios SET quantidade_emprestimos = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, novaQuantidade);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {}
    }
}