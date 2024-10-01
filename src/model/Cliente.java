package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    private static Scanner sc = new Scanner(System.in);
    public static List<Cliente> clientes = new ArrayList<>(); 
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;

    public Cliente(String nome, String cpf, String endereco, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public static void cadastrarCliente() {
        System.out.print("Nome completo: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        Cliente cliente = new Cliente(nome, cpf, endereco, telefone);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public static void visualizarClientes() {
        System.out.println("---- Lista de Clientes ----");
        for (Cliente cliente : clientes) {
            System.out.println("Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf() +
                    ", Endereço: " + cliente.getEndereco() + ", Telefone: " + cliente.getTelefone());
        }
    }

    public static void editarCliente() {
        System.out.print("Informe o CPF do cliente a ser editado: ");
        String cpf = sc.nextLine();
        Cliente cliente = encontrarClientePorCpf(cpf);
        
        if (cliente != null) {
            System.out.print("Novo Nome (deixe vazio para manter): ");
            String nome = sc.nextLine();
            System.out.print("Novo Endereço (deixe vazio para manter): ");
            String endereco = sc.nextLine();
            System.out.print("Novo Telefone (deixe vazio para manter): ");
            String telefone = sc.nextLine();

            if (!nome.isEmpty()) cliente.nome = nome;
            if (!endereco.isEmpty()) cliente.endereco = endereco;
            if (!telefone.isEmpty()) cliente.telefone = telefone;

            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void removerCliente() {
        System.out.print("Informe o CPF do cliente a ser removido: ");
        String cpf = sc.nextLine();
        Cliente cliente = encontrarClientePorCpf(cpf);
        
        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static Cliente encontrarClientePorCpf(String cpf) { 
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
}
