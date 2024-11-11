package modelo;

public class Produto {
    private Integer id;
    private String nome;
    private Double preco;
    private Integer quantidadeEstoque;
    private String categoria;

    public Produto(String nome, Double preco, Integer quantidadeEstoque, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto {" +
                "ID: " + id +
                ", Nome: '" + nome + '\'' +
                ", Preço: R$" + preco +
                ", Quantidade em estoque: " + quantidadeEstoque +
                ", Categoria: '" + categoria + '\'' +
                '}';
    }


}
