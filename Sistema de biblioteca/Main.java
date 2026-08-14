import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        boolean rodando = true;

       
        do {
            System.out.println(" :'.              .': ");
            System.out.println(" :   :..........:   :");
            System.out.println(" =    BIBLIOTECA    =");
            System.out.println("  ..................");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Cadastrar usuario");
            System.out.println("3 - Emprestar livro");
            System.out.println("4 - Devolver livro");
            System.out.println("5 - Listar livros");
            System.out.println("6 - Listar emprestimos");
            System.out.println("7 - Listar Usuarios");
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
                    
                    // Adiciona o livro passando direto pra biblioteca
                    biblioteca.adicionarLivro(new Livro(idLivro, titulo, true));
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
                        System.out.print("Digite a Matricula: ");

                    } else if (tipo == 2) {
                        System.out.print("Digite a Disciplina: ");
                    }
                    String dadoEspecifico = sc.nextLine();
            

                    // Usa o Factory para criar o tipo certo sem entulhar o Main de ifs
                    Usuario novoUsuario = UsuarioFactory.criarUsuario(tipo, idUsuario, nome, email, dadoEspecifico);
                    
                    if (novoUsuario != null) {
                        biblioteca.adicionarUsuario(novoUsuario);
                        System.out.println("Usuario cadastrado com sucesso!");
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
                    System.out.print("Digite a data do emprestimo (dd/MM/yyyy): ");
                    String dataEmprestimo = sc.nextLine();
                    
                    biblioteca.emprestarLivro(idLivroAluguel, idUsuAluguel, dataEmprestimo);
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

                case 7:
                    biblioteca.listarUsuarios();
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    rodando = false;
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
            
        } while (rodando);
        
        sc.close();
    }
}