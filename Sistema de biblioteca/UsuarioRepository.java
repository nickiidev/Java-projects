public interface UsuarioRepository {
    void salvar(Usuario usuario);
    Usuario buscarPorId(int id);
    void atualizarQuantidadeEmprestimos(int id, int novaQuantidade);
}