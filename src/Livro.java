import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Livro {
    private static Scanner sc = new Scanner(System.in);
    public static List<Livro> livros = new ArrayList<>(); // Agora é public
    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int quantidadeDisponivel;

    public Livro(int id, String titulo, String autor, int anoPublicacao, int quantidadeDisponivel) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void diminuirQuantidade() {
        if (quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
        } else {
            System.out.println("Quantidade não disponível para empréstimo.");
        }
    }

    public void aumentarQuantidade() {
        quantidadeDisponivel++;
    }

    public static void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Ano de Publicação: ");
        int anoPublicacao = Integer.parseInt(sc.nextLine());
        System.out.print("Quantidade Disponível: ");
        int quantidadeDisponivel = Integer.parseInt(sc.nextLine());

        int id = livros.size() + 1; // Gera um ID sequencial
        Livro livro = new Livro(id, titulo, autor, anoPublicacao, quantidadeDisponivel);
        livros.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    public static void visualizarLivros() {
        System.out.println("---- Lista de Livros ----");
        for (Livro livro : livros) {
            System.out.println("ID: " + livro.getId() + ", Título: " + livro.getTitulo() +
                    ", Autor: " + livro.getAutor() + ", Ano: " + livro.getAnoPublicacao() +
                    ", Disponíveis: " + livro.getQuantidadeDisponivel());
        }
    }

    public static void editarLivro() {
        System.out.print("Informe o ID do livro a ser editado: ");
        int id = Integer.parseInt(sc.nextLine());
        Livro livro = encontrarLivroPorId(id);
        
        if (livro != null) {
            System.out.print("Novo Título (deixe vazio para manter): ");
            String titulo = sc.nextLine();
            System.out.print("Novo Autor (deixe vazio para manter): ");
            String autor = sc.nextLine();
            System.out.print("Novo Ano de Publicação (0 para manter): ");
            int anoPublicacao = Integer.parseInt(sc.nextLine());
            System.out.print("Nova Quantidade Disponível (-1 para manter): ");
            int quantidadeDisponivel = Integer.parseInt(sc.nextLine());

            if (!titulo.isEmpty()) livro.titulo = titulo;
            if (!autor.isEmpty()) livro.autor = autor;
            if (anoPublicacao != 0) livro.anoPublicacao = anoPublicacao;
            if (quantidadeDisponivel != -1) livro.quantidadeDisponivel = quantidadeDisponivel;

            System.out.println("Livro atualizado com sucesso!");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public static void removerLivro() {
        System.out.print("Informe o ID do livro a ser removido: ");
        int id = Integer.parseInt(sc.nextLine());
        Livro livro = encontrarLivroPorId(id);
        
        if (livro != null) {
            livros.remove(livro);
            System.out.println("Livro removido com sucesso!");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public static Livro encontrarLivroPorId(int id) { // Tornado público
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }
}
