package ui;

import gerenciador.GerenciadorProdutos;
import modelo.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuProdutos {
    private final Scanner scanner;
    private final GerenciadorProdutos gerenciador;

    public MenuProdutos() {
        this.scanner = new Scanner(System.in);
        this.gerenciador = new GerenciadorProdutos();
    }

    public void exibirMenu() {
        Integer valorInserido;
        System.out.println("O que deseja fazer?\n\n" +
                "1. Cadastrar Produto\n" +
                "2. Buscar Produto por ID\n" +
                "3. Listar Todos os Produtos\n" +
                "4. Atualizar Produto\n" +
                "5. Deletar Produto\n" +
                "6. Buscar por Nome\n" +
                "7. Buscar por Categoria\n" +
                "8. Sair");

        valorInserido = lerEntradaInteira(scanner.next());

        switch(valorInserido) {
            case 1:
                cadastrarProduto();
                break;
            case 2:
                buscarProduto();
                break;
            case 3:
                listarProdutos();
                break;
            case 4:
                atualizarProduto();
                break;
            case 5:
                deletarProduto();
                break;
            case 6:
                buscarPorNome();
                break;
            case 7:
                buscarPorCategoria();
                break;
            default:
                break;
        }
    }

    private Produto solicitarDados(Integer tipoOperacao) {
        String valorInserido;
        String nome;
        Double preco;
        Integer quantidadeEstoque;
        String categoria;

        while (true) {
            System.out.println("Informe o nome do produto: ");
            valorInserido = scanner.next();
            if ((valorInserido.isBlank() && tipoOperacao == 1) || valorInserido.length() < 2) {
                System.out.println("O nome do produto é inválido. Tente novamente.\n");
            } else {
                nome = valorInserido;
                break;
            }
        }

        while (true) {
            System.out.println("Informe o preço do produto: R$");
            valorInserido = scanner.next();
            preco = lerEntradaDouble(valorInserido);
            if (preco <= 0 && preco != null) {
                System.out.println("O valor inserio deve ser maior do que zero. Tente novamente.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Informe a quantidade em estoque: ");
            valorInserido = scanner.next();
            quantidadeEstoque = lerEntradaInteira(valorInserido);
            if (quantidadeEstoque < 0 && quantidadeEstoque != null) {
                System.out.println("A quantidade em estoque não pode ser negativa. Tente inserir outra quantidade.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Informe a categoria do produto: ");
            valorInserido = scanner.next();
            if (valorInserido.isBlank() && tipoOperacao == 1) {
                System.out.println("A categoria do produto é inválido. Tente novamente.\n");
            } else {
                categoria = valorInserido;
                break;
            }
        }

        return new Produto(nome, preco, quantidadeEstoque, categoria);
    }

    private void cadastrarProduto() {
        gerenciador.criar(solicitarDados(1));
    }

    private void buscarProduto() {
        System.out.println("Informe o ID a ser pesquisado: ");
        System.out.println(gerenciador.buscarPorId(scanner.nextInt()));
        scanner.next();
        System.out.println("Deseja procurar outro produto? S/N");
        if (scanner.next().equalsIgnoreCase("S")) {
            buscarProduto();
        } else {
            System.out.println("Voltando ao menu principal.");
        }
    }

    private void listarProdutos() {
        List<Produto> produtos = gerenciador.listarTodos();
        if (produtos.isEmpty()) {
            System.out.println("Sem produtos cadastrados.");
        } else {
            for (Produto p : produtos) {
                System.out.println(p.toString());
            }
        }
    }

    private void atualizarProduto() {
        Integer intInformado;
        System.out.println("Informe o ID do produto a ser atualizado: ");
        intInformado = scanner.nextInt();
        scanner.next();
        if (gerenciador.buscarPorId(intInformado) == null) {
            System.out.println("O produto com o ID informado não existe.");
        } else {
            gerenciador.atualizar(solicitarDados(2));
            System.out.println("Produto atualizado com sucesso!");
        }
    }

    private void deletarProduto() {
        Integer intInformado;
        System.out.println("Informe o ID do produto a ser deletado: ");
        intInformado = scanner.nextInt();
        scanner.next();
        if (gerenciador.buscarPorId(intInformado) == null) {
            System.out.println("O produto com o ID informado não existe.");
        } else {
            if (gerenciador.deletar(intInformado)) {
                System.out.println("Produto deletado com sucesso!");
            } else {
                System.out.println("Não foi possível deletar o produto.");
            }
        }
    }

    private void buscarPorNome() {
        String valorInserido;
        List<Produto> produtos;
        System.out.println("Informe o nome dos produtos a serem pesquisados: ");
        valorInserido = scanner.next();
        if (valorInserido.length() < 2) {
            System.out.println("O nome do produto deve conter no mínimo 2 caracteres.");
        } else {
            produtos = gerenciador.buscarPorNome(valorInserido);
            if (produtos.isEmpty()) {
                System.out.println("Não há produtos cadastrados com este nome.");
            } else {
                for (Produto p : produtos) {
                    System.out.println(p.toString());
                }
            }
        }
    }

    private void buscarPorCategoria() {
        String valorInserido;
        List<Produto> produtos;
        System.out.println("Informe a categoria dos produtos a serem pesquisados: ");
        valorInserido = scanner.next();
        if (valorInserido.isBlank()) {
            System.out.println("A categoria do produto não pode ser vazia.");
        } else {
            produtos = gerenciador.buscarPorCategoria(valorInserido);
            if (produtos.isEmpty()) {
                System.out.println("Não há produtos cadastrados com esta categoria.");
            } else {
                for (Produto p : produtos) {
                    System.out.println(p.toString());
                }
            }
        }
    }

    private Integer lerEntradaInteira(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("O número informado é inválido. " + e.getMessage());
            return null;
        }
    }

    private Double lerEntradaDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.out.println("O número informado é inválido. " + e.getMessage());
            return null;
        }
    }
}
