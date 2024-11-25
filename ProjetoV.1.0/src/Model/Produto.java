package model;

public class Produto {
    private int idProduto;
    private String nome;
    private String descricao;
    private double preco;
    private int estoque;

    public Produto(int idProduto, String nome, String descricao, double preco, int estoque) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    @Override
    public String toString() {
        return "Produto{idProduto=" + idProduto + ", nome='" + nome + "', descricao='" + descricao + "', preco=" + preco + ", estoque=" + estoque + "}";
    }
}
