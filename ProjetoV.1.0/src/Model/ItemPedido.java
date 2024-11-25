package model;

public class ItemPedido {
    private int idItem;
    private Pedido pedido;
    private Produto produto;
    private int quantidade;
    private double subtotal;

    public ItemPedido(int idItem, Pedido pedido, Produto produto, int quantidade) {
        this.idItem = idItem;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = produto.getPreco() * quantidade;
    }

    public int getIdItem() {
        return idItem;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return "ItemPedido{idItem=" + idItem + ", produto=" + produto.getNome() + ", quantidade=" + quantidade + ", subtotal=" + subtotal + "}";
    }
}
