// O Factory é o responsável por decidir qual objeto instanciar.
public class UsuarioFactory {
    
    // Recebe os dados e o tipo, e devolve o usuário correto já montado.
    public static Usuario criarUsuario(int tipo, int id, String nome, String email, String dadoEspecifico) {
        if (tipo == 1) {
            // Se for 1, o dadoEspecifico é a matrícula e ele cria um Aluno.
            return new Aluno(id, nome, email, dadoEspecifico); 
        } else if (tipo == 2) {
            // Se for 2, o dadoEspecifico é a disciplina e ele cria um Professor.
            return new Professor(id, nome, email, dadoEspecifico); 
        }
        // Retorna nulo se o usuário digitar uma opção inválida.
        return null;
    }
}