package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private Cliente cliente;
    private String data;
    private double total;
    private EnumStatus status;
    private List<ItemPedido> itens;

    public Pedido(int idPedido, Cliente cliente, String data) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.data = data;
        this.itens = new ArrayList<>();
        this.status = EnumStatus.PENDENTE;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getData() {
        return data;
    }

    public double getTotal() {
        return total;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
        this.total += item.getSubtotal();
    }

    @Override
    public String toString() {
        return "Pedido{idPedido=" + idPedido + ", cliente=" + cliente.getNome() + ", data='" + data + "', total=" + total + ", status=" + status + "}";
    }
}

