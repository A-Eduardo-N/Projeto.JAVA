package service;

import Model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class SistemaVenda {
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private List<Pedido> pedidos;
    private List<Fornecedor> fornecedores;
    private List<String> relatorio;

    public SistemaVenda() {
        clientes = new ArrayList<>();
        produtos = new ArrayList<>();
        pedidos = new ArrayList<>();
        fornecedores = new ArrayList<>();
        relatorio = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void adicionarFornecedor(Fornecedor fornecedor) {
        fornecedores.add(fornecedor);
    }

    public void adicionarRelatorio(String novoRelatorio) {
        LocalDateTime datahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataHoraFormatada = datahora.format(formatter);
        relatorio.add(dataHoraFormatada + " | " + novoRelatorio);
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public List<Pedido> listarPedidos() {
        return pedidos;
    }

    public List<Fornecedor> listarFornecedores() {
        return fornecedores;
    }

    public void atualizarProduto(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getIdProduto() == produto.getIdProduto()) {
                produtos.set(i, produto);
                break;
            }
        }
    }

    public List<String> getRelatorio() {
        return relatorio;
    }

    public Optional<Produto> encontrarProduto(int id) {
        return produtos.stream()
                .filter(produto -> produto.getIdProduto() == id)
                .findFirst();
    }

    public void removerProduto(int idProduto) {
        produtos.removeIf(p -> p.getIdProduto() == idProduto);
    }

    // Similar para Cliente, Pedido, Fornecedor
}