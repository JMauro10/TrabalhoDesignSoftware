import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class Biblioteca {
    private static Scanner sc = new Scanner(System.in);

    public static void menu() {
        while (true) {
            System.out.println("---- Sistema de Gerenciamento de Biblioteca ----");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Livros");
            System.out.println("3. Gerenciar Empréstimos");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = Integer.parseInt(sc.nextLine());

            switch (escolha) {
                case 1:
                    gerenciarClientes();
                    break;
                case 2:
                    gerenciarLivros();
                    break;
                case 3:
                    gerenciarEmprestimos();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void gerenciarClientes() {
        while (true) {
            System.out.println("---- Gerenciar Clientes ----");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Visualizar Clientes");
            System.out.println("3. Editar Cliente");
            System.out.println("4. Remover Cliente");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int escolha = Integer.parseInt(sc.nextLine());

            switch (escolha) {
                case 1:
                    Cliente.cadastrarCliente();
                    break;
                case 2:
                    Cliente.visualizarClientes();
                    break;
                case 3:
                    Cliente.editarCliente();
                    break;
                case 4:
                    Cliente.removerCliente();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void gerenciarLivros() {
        while (true) {
            System.out.println("---- Gerenciar Livros ----");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Visualizar Livros");
            System.out.println("3. Editar Livro");
            System.out.println("4. Remover Livro");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int escolha = Integer.parseInt(sc.nextLine());

            switch (escolha) {
                case 1:
                    Livro.cadastrarLivro();
                    break;
                case 2:
                    Livro.visualizarLivros();
                    break;
                case 3:
                    Livro.editarLivro();
                    break;
                case 4:
                    Livro.removerLivro();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void gerenciarEmprestimos() {
        while (true) {
            System.out.println("---- Gerenciar Empréstimos ----");
            System.out.println("1. Realizar Empréstimo");
            System.out.println("2. Devolver Livro");
            System.out.println("3. Relatório de Livros Mais Emprestados");
            System.out.println("4. Relatório de Atrasos");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int escolha = Integer.parseInt(sc.nextLine());

            switch (escolha) {
                case 1:
                    System.out.print("Informe o CPF do cliente: ");
                    String cpfCliente = sc.nextLine();
                    Cliente cliente = Cliente.encontrarClientePorCpf(cpfCliente);
                    if (cliente != null) {
                        System.out.print("Informe o ID do livro: ");
                        int idLivro = Integer.parseInt(sc.nextLine());
                        Livro livro = Livro.encontrarLivroPorId(idLivro);
                        if (livro != null && livro.getQuantidadeDisponivel() > 0) {
                            System.out.print("Informe a data de devolução prevista (formato: yyyy-mm-dd): ");
                            String dataDevolucaoPrevistaStr = sc.nextLine();
                            Date dataDevolucaoPrevista;
                            try {
                                dataDevolucaoPrevista = new SimpleDateFormat("yyyy-MM-dd").parse(dataDevolucaoPrevistaStr);
                                Emprestimo.realizarEmprestimo(cliente, livro, dataDevolucaoPrevista);
                            } catch (Exception e) {
                                System.out.println("Data inválida. Tente novamente.");
                            }
                        } else {
                            System.out.println("Livro não disponível para empréstimo.");
                        }
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 2:
                    System.out.print("Informe o CPF do cliente: ");
                    String cpfDevolucao = sc.nextLine();
                    Cliente clienteDevolucao = Cliente.encontrarClientePorCpf(cpfDevolucao);
                    if (clienteDevolucao != null) {
                        System.out.print("Informe o ID do livro: ");
                        int idLivroDevolucao = Integer.parseInt(sc.nextLine());
                        Livro livroDevolucao = Livro.encontrarLivroPorId(idLivroDevolucao);
                        if (livroDevolucao != null) {
                            Emprestimo.devolverLivro(clienteDevolucao, livroDevolucao);
                        } else {
                            System.out.println("Livro não encontrado.");
                        }
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 3:
                    Emprestimo.relatorioLivrosMaisEmprestados();
                    break;
                case 4:
                    Emprestimo.relatorioAtrasos();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
