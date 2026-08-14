import java.util.List;

// A interface diz O QUE o repositório deve fazer, mas não COMO.
public interface LivroRepository {
    void salvar(Livro livro);
    Livro buscarPorId(int id);
    void atualizarDisponibilidade(int id, boolean disponivel);
    List<Livro> listarTodos();
}