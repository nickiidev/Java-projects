import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Implementa a interface comunicando de verdade com o SQLite.
public class LivroRepositorySQLite implements LivroRepository {
    private Connection conn;

    public LivroRepositorySQLite() {
        // Pega a conexão através do Singleton
        this.conn = DatabaseConnection.getInstance();
    }

    @Override
    public void salvar(Livro livro) {
        String sql = "INSERT INTO livros(id, titulo, disponivel) VALUES(?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Troca os '?' pelos dados do livro
            pstmt.setInt(1, livro.getId());
            pstmt.setString(2, livro.getTitulo());
            pstmt.setInt(3, livro.isDisponivel() ? 1 : 0); // SQLite não tem boolean, usa 1 ou 0
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar livro: " + e.getMessage());
        }
    }

    @Override
    public Livro buscarPorId(int id) {
        String sql = "SELECT * FROM livros WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            // Se encontrar, monta o objeto Livro e devolve
            if (rs.next()) {
                return new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getInt("disponivel") == 1);
            }
        } catch (SQLException e) {}
        return null;
    }

    @Override
    public void atualizarDisponibilidade(int id, boolean disponivel) {
        String sql = "UPDATE livros SET disponivel = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, disponivel ? 1 : 0);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {}
    }

    @Override
    public List<Livro> listarTodos() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            // Roda enquanto tiver livros cadastrados no banco
            while (rs.next()) {
                livros.add(new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getInt("disponivel") == 1));
            }
        } catch (SQLException e) {}
        return livros;
    }
}