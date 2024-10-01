package controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Livro;
import model.Cliente;

public class Emprestimo {
    private static List<Emprestimo> emprestimos = new ArrayList<>();
    private static Map<Livro, Integer> livrosMaisEmprestados = new HashMap<>();
    
    private Cliente cliente;
    private Livro livro;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    private Date dataDevolucaoReal;

    public Emprestimo(Cliente cliente, Livro livro, Date dataDevolucaoPrevista) {
        this.cliente = cliente;
        this.livro = livro;
        this.dataEmprestimo = new Date();
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = null;

        livro.diminuirQuantidade(); 
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public Date getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public static void realizarEmprestimo(Cliente cliente, Livro livro, Date dataDevolucaoPrevista) {
        Emprestimo emprestimo = new Emprestimo(cliente, livro, dataDevolucaoPrevista);
        emprestimos.add(emprestimo);
        registrarLivroMaisEmprestado(livro); 
        System.out.println("Empréstimo realizado com sucesso!");
    }

    public static void devolverLivro(Cliente cliente, Livro livro) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCliente().equals(cliente) && emprestimo.getLivro().equals(livro)) {
                emprestimo.dataDevolucaoReal = new Date();
                livro.aumentarQuantidade(); 
                System.out.println("Livro devolvido com sucesso!");
                return;
            }
        }
        System.out.println("Empréstimo não encontrado.");
    }

    private static void registrarLivroMaisEmprestado(Livro livro) {
        livrosMaisEmprestados.put(livro, livrosMaisEmprestados.getOrDefault(livro, 0) + 1);
    }

    public static void relatorioLivrosMaisEmprestados() {
        System.out.println("---- Relatório de Livros Mais Emprestados ----");
        for (Map.Entry<Livro, Integer> entry : livrosMaisEmprestados.entrySet()) {
            System.out.println("Título: " + entry.getKey().getTitulo() + ", Autor: " + entry.getKey().getAutor() +
                    ", Total de Empréstimos: " + entry.getValue());
        }
    }

    public static void relatorioAtrasos() {
        System.out.println("---- Relatório de Atrasos ----");
        Date agora = new Date();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.dataDevolucaoReal == null && emprestimo.getDataDevolucaoPrevista().before(agora)) {
                System.out.println("Título: " + emprestimo.getLivro().getTitulo() + ", Cliente: " + emprestimo.getCliente().getNome());
            }
        }
    }
}

