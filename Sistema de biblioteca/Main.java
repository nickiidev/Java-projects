import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        boolean rodando = true;


        do {
            System.out.println("\n  :'.            .': ");
            System.out.println(" :   :..........:   :");
            System.out.println("\n =    BIBLIOTECA    =");
            System.out.println("\n  ..................");
            System.out.println("\n1 - Cadastrar livro");
            System.out.println("2 - Cadastrar usuario");
            System.out.println("3 - Emprestar livro");
            System.out.println("4 - Devolver livro");
            System.out.println("5 - Listar livros");
            System.out.println("6 - Listar emprestimos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            
            int opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("ID do Livro: ");
                    int idLivro = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    
                
                    biblioteca.adicionarLivro(new Livro(idLivro, titulo));
                    System.out.println("Livro cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("Qual tipo? (1 - Aluno | 2 - Professor): ");
                    int tipo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("ID do Usuario: ");
                    int idUsuario = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    if (tipo == 1) {
                        System.out.print("Matricula: ");
                        String matricula = sc.nextLine();
                        biblioteca.adicionarUsuario(new Aluno(idUsuario, nome, email, matricula));
                        System.out.println("Aluno cadastrado!");
                    } else if (tipo == 2) {
                        System.out.print("Disciplina: ");
                        String disciplina = sc.nextLine();
                        biblioteca.adicionarUsuario(new Professor(idUsuario, nome, email, disciplina));
                        System.out.println("Professor cadastrado!");
                    } else {
                        System.out.println("Tipo invalido.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do Livro: ");
                    int idLivroAluguel = sc.nextInt();
                    System.out.print("Digite o ID do Usuario: ");
                    int idUsuAluguel = sc.nextInt();
                    sc.nextLine();

                    Livro livroAchado = biblioteca.buscarLivro(idLivroAluguel);
                    Usuario usuarioAchado = biblioteca.buscarUsuario(idUsuAluguel);
                    
                    if (livroAchado != null && usuarioAchado != null) {
                        System.out.print("Digite a data do emprestimo (dd/MM/yyyy): ");
                        String dataEmprestimo = sc.nextLine();
                        System.out.println("Iniciando emprestimo para: " + usuarioAchado.getNome());
                        biblioteca.emprestarLivro(idLivroAluguel, idUsuAluguel, dataEmprestimo);
                    } else {
                        System.out.println("Erro: O ID do Livro ou do Usuario não existe no sistema.");
                    }
                    break;

                case 4:
                    System.out.print("ID do Livro que esta sendo devolvido: ");
                    int idLivroDevolucao = sc.nextInt();
                    biblioteca.devolverLivro(idLivroDevolucao);
                    break;

                case 5:
                    biblioteca.listarLivros();
                    break;

                case 6:
                    biblioteca.listarEmprestimos();
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
        } while (rodando);
        
        sc.close();
    }
}