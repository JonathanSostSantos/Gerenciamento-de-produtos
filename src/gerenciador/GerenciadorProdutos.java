package gerenciador;

import modelo.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GerenciadorProdutos {
    private List<Produto> produtos;
    private Integer proximoId;

    public GerenciadorProdutos() {
        this.produtos = new ArrayList<>();
        proximoId = 1;
    }

    public void criar(Produto produto) {
        produto.setId(proximoId);
        produtos.add(produto);
        proximoId++;
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
        return produtos;
    }

    public boolean atualizar(Produto produto) {
        Optional<Produto> produtoASerAtualizado = produtos.stream().filter(prod -> prod.getId().equals(produto.getId())).findFirst();

        for (Produto p : produtos) {
            if (produto.getId().equals(p.getId())) {
                if (!produto.getNome().isBlank()) {
                    p.setNome(p.getNome());
                }
                if (!produto.getPreco().isNaN()) {
                    p.setPreco(produto.getPreco());
                }
                if (!produto.getCategoria().isBlank()) {
                    p.setCategoria(produto.getCategoria());
                }
                if (!produto.getQuantidadeEstoque().equals(-1)) {
                    p.setQuantidadeEstoque(produto.getQuantidadeEstoque());
                }

                //produtos.set(produtos.indexOf(p), p);
                return true;
            }
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
}
