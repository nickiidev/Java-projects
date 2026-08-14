import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

// O Singleton garante que o programa inteiro use a mesma conexão com o banco.
public class DatabaseConnection {
    private static Connection instance;

    // Construtor privado impede que alguém crie um "new DatabaseConnection()" fora daqui.
    private DatabaseConnection() {}

    // Método que o resto do sistema chama para pegar a conexão.
    public static Connection getInstance() {
        if (instance == null) {
            try {
                // Cria ou conecta ao arquivo biblioteca.db na pasta do projeto.
                String url = "jdbc:sqlite:biblioteca.db";
                instance = DriverManager.getConnection(url);
                // Assim que conecta, já verifica se precisa criar as tabelas.
                criarTabelas();
            } catch (Exception e) {
                System.out.println("Erro ao conectar no banco: " + e.getMessage());
            }
        }
        return instance;
    }

    // Cria as tabelas no banco de dados SQLite caso elas ainda não existam.
    private static void criarTabelas() {
        try (Statement stmt = instance.createStatement()) {
            // Tabela de Livros
            stmt.execute("CREATE TABLE IF NOT EXISTS livros (id INTEGER PRIMARY KEY, titulo TEXT, disponivel INTEGER)");
            // Tabela de Usuários (guarda tanto Aluno quanto Professor)
            stmt.execute("CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY, nome TEXT, email TEXT, tipo TEXT, matricula TEXT, disciplina TEXT, quantidade_emprestimos INTEGER)");
            // Tabela de Empréstimos (relaciona quem pegou qual livro)
            stmt.execute("CREATE TABLE IF NOT EXISTS emprestimos (id INTEGER PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER, id_livro INTEGER, data_emprestimo TEXT, data_devolucao TEXT, status TEXT)");
        } catch (Exception e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}