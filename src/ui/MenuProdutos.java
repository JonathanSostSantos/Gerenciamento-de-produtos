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
        Boolean sistemaAtivo = true;

        gerenciador.criar(new Produto("Smartwatch X3", 799.90, 150, "Tecnologia"));
        gerenciador.criar(new Produto("Fone de Ouvido Bluetooth MiroSound", 249.90, 300, "Tecnologia"));
        gerenciador.criar(new Produto("Carregador Solar HyperCharge", 199.90, 200, "Tecnologia"));
        gerenciador.criar(new Produto("Notebook SkyPro 15", 3299.00, 80, "Tecnologia"));
        gerenciador.criar(new Produto("Tênis Urban Step", 179.90, 500, "Moda"));
        gerenciador.criar(new Produto("Jaqueta de Couro Legacy", 599.90, 120, "Moda"));
        gerenciador.criar(new Produto("Óculos de Sol Vibe 2024", 149.90, 350, "Moda"));
        gerenciador.criar(new Produto("Vestido Flor de Lótus", 229.90, 180, "Moda"));
        gerenciador.criar(new Produto("Abajur de Mesa Lumina", 149.90, 90, "Casa e Decoração"));
        gerenciador.criar(new Produto("Conjunto de Toalhas Luxo Premium", 89.90, 400, "Casa e Decoração"));

        while (sistemaAtivo) {
            System.out.println("O que deseja fazer?\n\n" +
                    "1. Cadastrar Produto\n" +
                    "2. Buscar Produto por ID\n" +
                    "3. Listar Todos os Produtos\n" +
                    "4. Atualizar Produto\n" +
                    "5. Deletar Produto\n" +
                    "6. Buscar por Nome\n" +
                    "7. Buscar por Categoria\n" +
                    "8. Sair");

            valorInserido = lerEntradaInteira(scanner.nextLine());

            if (valorInserido != null) {
                switch (valorInserido) {
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
                        sistemaAtivo = false;
                        break;
                }
            }
        }
    }

    private Produto solicitarDados(Integer tipoOperacao, Integer idProduto) {
        String valorInserido;
        String nome;
        Double preco;
        Integer quantidadeEstoque;
        String categoria;
        Produto produtoASerAtualizado = new Produto();

        if (tipoOperacao == 2) {
            produtoASerAtualizado = gerenciador.buscarPorId(idProduto);
        }

        while (true) {
            if (tipoOperacao == 2) {
                System.out.println("Nome atual do produto: " + produtoASerAtualizado.getNome());
            }
            System.out.printf("Informe o%s nome do produto: %n", tipoOperacao == 2 ? " novo" : "");
            valorInserido = scanner.next().trim();
            if ((valorInserido.length() < 2 && tipoOperacao == 1) || (valorInserido.length() > 0 && valorInserido.length() < 2 && tipoOperacao == 2)) {
                System.out.println("O nome do produto deve conter pelo menos 2 caracteres.\n");
            } else {
                nome = valorInserido;
                break;
            }
        }

        while (true) {
            if (tipoOperacao == 2) {
                System.out.println("Preço atual do produto: " + produtoASerAtualizado.getPreco());
            }
            System.out.printf("Informe o%s preço do produto: %n", tipoOperacao == 2 ? " novo" : "");
            preco = lerEntradaDouble(scanner.next().trim());
            if (preco != null) {
                if (preco <= 0) {
                    System.out.println("O valor inserido deve ser maior do que zero. Tente novamente.");
                } else {
                    break;
                }
            }
        }

        while (true) {
            if (tipoOperacao == 2) {
                System.out.println("Quantidade em estoque atual do produto: " + produtoASerAtualizado.getQuantidadeEstoque());
            }
            System.out.printf("Informe a%s quantidade em estoque do produto: %n", tipoOperacao == 2 ? " nova" : "");
            quantidadeEstoque = lerEntradaInteira(scanner.next().trim());
            if (quantidadeEstoque != null) {
                if (quantidadeEstoque < 0) {
                    System.out.println("A quantidade em estoque não pode ser negativa. Tente inserir outra quantidade.");
                } else {
                    break;
                }
            }
        }

        while (true) {
            if (tipoOperacao == 2) {
                System.out.println("Categoria atual do produto: " + produtoASerAtualizado.getCategoria());
            }
            System.out.printf("Informe a%s categoria do produto: %n", tipoOperacao == 2 ? " nova" : "");
            valorInserido = scanner.next().trim();
            if (valorInserido.isBlank() && tipoOperacao == 1) {
                System.out.println("A categoria do produto é inválida. Tente novamente.\n");
            } else {
                categoria = valorInserido;
                break;
            }
        }

        return new Produto(nome, preco, quantidadeEstoque, categoria);
    }

    private Produto solicitarDados(Integer tipoOperacao) {
        return solicitarDados(tipoOperacao, 0);
    }

    private void cadastrarProduto() {
        gerenciador.criar(solicitarDados(1));
    }

    private void buscarProduto() {
        Integer intInformado;
        System.out.println("Informe o ID a ser pesquisado: ");
        intInformado = lerEntradaInteira(scanner.next().trim());
        if (intInformado != null) {
            System.out.println(gerenciador.buscarPorId(intInformado));
            System.out.println("Deseja procurar outro produto? S/N");
            if (scanner.next().trim().equalsIgnoreCase("S")) {
                buscarProduto();
            } else {
                System.out.println("Voltando ao menu principal.");
            }
        }
    }

    private void listarProdutos() {
        List<Produto> produtos = gerenciador.listarTodos();
        if (produtos.isEmpty()) {
            System.out.println("Sem produtos cadastrados.");
        } else {
            System.out.println("====Produtos====\n");

            for (Produto p : produtos) {
                System.out.println(p.toString());
            }
            System.out.println();
        }
    }

    private void atualizarProduto() {
        Integer intInformado;
        Produto novoProduto;
        System.out.println("Informe o ID do produto a ser atualizado: ");
        intInformado = lerEntradaInteira(scanner.next().trim());
        if (intInformado != null) {
            if (gerenciador.buscarPorId(intInformado) == null) {
                System.out.println("O produto com o ID informado não existe.");
            } else {
                novoProduto = solicitarDados(2, intInformado);
                novoProduto.setId(intInformado);
                gerenciador.atualizar(novoProduto);
            }
        }
    }

    private void deletarProduto() {
        Integer intInformado;
        System.out.println("Informe o ID do produto a ser deletado: ");
        intInformado = lerEntradaInteira(scanner.next().trim());
        if (intInformado != null) {
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
    }

    private void buscarPorNome() {
        String valorInserido;
        List<Produto> produtos;
        System.out.println("Informe o nome dos produtos a serem pesquisados: ");
        valorInserido = scanner.nextLine().trim();
        if (valorInserido.length() < 2) {
            System.out.println("O nome do produto deve conter no mínimo 2 caracteres.");
        } else {
            produtos = gerenciador.buscarPorNome(valorInserido);
            if (produtos.isEmpty()) {
                System.out.println("Não há produtos cadastrados com este nome.");
            } else {
                System.out.println("====Produtos====\n");

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
        valorInserido = scanner.nextLine().trim();
        if (valorInserido.isBlank()) {
            System.out.println("A categoria do produto não pode ser vazia.");
        } else {
            produtos = gerenciador.buscarPorCategoria(valorInserido);
            if (produtos.isEmpty()) {
                System.out.println("Não há produtos cadastrados com esta categoria.");
            } else {
                System.out.println("====Produtos====\n");

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
