package ui;

import gerenciador.GerenciadorProdutos;
import modelo.Produto;

import java.util.Scanner;

public class MenuProdutos {
    private Scanner scanner;
    private GerenciadorProdutos gerenciador;

    public MenuProdutos() {
        this.scanner = new Scanner(System.in);
        this.gerenciador = new GerenciadorProdutos();
    }

    public void exibirMenu() {
        while(true) {
            System.out.println("");
        }
    }

    private void cadastrarProduto() {
        String valorInserido;
        Integer intInserido;
        String nome;
        Double preco;
        Integer quantidadeEstoque;
        String categoria;
        Produto produtoACadastrar = new Produto();

        while (true) {
            System.out.println("Informe o nome do produto: ");
            valorInserido = scanner.next();
            if (valorInserido.isBlank()) {
                System.out.println("O nome do produto é inválido. Tente novamente.\n");
            } else {
                nome = valorInserido;
                break;
            }
        }
    }
}
