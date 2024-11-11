package gerenciador;

import modelo.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GerenciadorProdutos {
    private List<Produto> produtos;
    private Integer proximoId;

    public void criar(Produto produto) {
        produto.setId(proximoId);
        produtos.add(produto);
    }

    public Produto buscarPorId(int id) {
        for(Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }

        return null;
    }

    public List<Produto> listarTodos() {
        List<Produto> produtosARetornar = produtos;
        return produtosARetornar;
    }

    public boolean atualizar(Produto produto) {
        Optional<Produto> produtoASerAtualizado = produtos.stream().filter(prod -> prod.getId().equals(produto.getId())).findFirst();

        if (produtoASerAtualizado != null) {
            produtos.set(produtos.indexOf(produtoASerAtualizado), produto);
            return true;
        }

        return false;
    }

    public boolean deletar(int id) {
        for(Produto p : produtos) {
            if (p.getId().equals(id)) {
                produtos.remove(p);
                return true;
            }
        }

        return false;
    }

    public List<Produto> buscarPorNome(String nome) {
        List<Produto> produtosParaRetornar = new ArrayList<>();

        for(Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                produtosParaRetornar.add(p);
            }
        }

        return produtosParaRetornar;
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        List<Produto> produtosParaRetornar = new ArrayList<>();

        for(Produto p : produtos) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                produtosParaRetornar.add(p);
            }
        }

        return produtosParaRetornar;
    }

    private void validarProduto(Produto produto) {
        if (produto.getNome().isEmpty()) {
            System.out.println("O nome do produto não pode ser vazio.");
        }
        if (produto.getPreco() < 0) {
            System.out.println("O valor do produto não pode ser negativo.");
        }
        if (produto.getQuantidadeEstoque() < 0) {
            System.out.println("A quantidade do produto não pode ser menor do que zero.");
        }
    }
}
