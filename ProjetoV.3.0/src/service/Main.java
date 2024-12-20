package service;

import Model.*;

import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class Main {

    private static SistemaVenda sistemaVenda = new SistemaVenda();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean rodando = true;

        while (rodando) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarCliente();
                    break;
                case 2:
                    adicionarProduto();
                    break;
                case 3:
                    adicionarPedido();
                    break;
                case 4:
                    atualizarProduto();
                    break;
                case 5:
                    removerProduto();
                    break;
                case 6:
                    listarDados();
                    break;
                case 7:
                    visualizarRelatorio();
                    break;
                case 0:
                    rodando = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1 - Adicionar Cliente");
        System.out.println("2 - Adicionar Produto");
        System.out.println("3 - Adicionar Pedido");
        System.out.println("4 - Atualizar Produto");
        System.out.println("5 - Remover Produto");
        System.out.println("6 - Listar Dados");
        System.out.println("7 - Visualizar Relatório de Mudanças");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static boolean validarEmail (String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return (email != null && email.matches(regex));
    }

    private static boolean validarTelefone (String telefone) {
        String regex2 = "^(?:\\(\\d{2}\\)\\s?)?\\d{4,5}-?\\d{4}$";
        return (telefone != null && telefone.matches(regex2));
    }

    private static void adicionarCliente() {
        System.out.println("\n### Adicionar Cliente ###");
        System.out.print("Digite o ID do Cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha após o ID
        System.out.print("Digite o nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email do Cliente (Ex: nome@dominio.com): ");
        String email = scanner.nextLine();
        System.out.print("Digite o telefone do Cliente (Ex: (11)91234-5678): ");
        String telefone = scanner.nextLine();
        System.out.print("Digite o endereço do Cliente: ");
        String endereco = scanner.nextLine();

        if (validarEmail(email)){
            if (validarTelefone(telefone)){
                Cliente cliente = new Cliente(idCliente, nome, email, telefone, endereco);
                sistemaVenda.adicionarCliente(cliente);
                sistemaVenda.adicionarRelatorio("Novo cliente: " + cliente);

                System.out.println("Cliente adicionado com sucesso!");
            }
            else {
                System.out.println("Telefone inválido! Formatação correta: (11)91234-5678");
            }
        }
        else {
            System.out.println("Email inválido! Formatação correta: nome@dominio.com");
        }
    }

    private static void adicionarProduto() {
        System.out.println("\n### Adicionar Produto ###");
        System.out.print("Digite o ID do Produto: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha após o ID
        System.out.print("Digite o nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a descrição do Produto: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite o preço do Produto: ");
        double preco = scanner.nextDouble();
        System.out.print("Digite a quantidade em estoque do Produto: ");
        int estoque = scanner.nextInt();

        Produto produto = new Produto(idProduto, nome, descricao, preco, estoque);
        sistemaVenda.adicionarProduto(produto);
        sistemaVenda.adicionarRelatorio("Novo produto: " + produto);

        System.out.println("Produto adicionado com sucesso!");
    }

    private static void adicionarPedido() {
        System.out.println("\n### Adicionar Pedido ###");
        System.out.print("Digite o ID do Pedido: ");
        int idPedido = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o ID do Cliente que fez o pedido: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
        Cliente cliente = null;
        for (Cliente c : sistemaVenda.listarClientes()) {
            if (c.getIdCliente() == idCliente) {
                cliente = c;
                break;
            }
        }

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        System.out.print("Digite a data do Pedido (formato: YYYY-MM-DD): ");
        String data = scanner.nextLine();

        Pedido pedido = new Pedido(idPedido, cliente, data);

        // Adicionar itens ao pedido
        boolean adicionarItens = true;
        while (adicionarItens) {
            System.out.print("Digite o ID do Produto para adicionar ao pedido: ");
            int idProduto = scanner.nextInt();
            scanner.nextLine();
            Produto produto = null;
            for (Produto p : sistemaVenda.listarProdutos()) {
                if (p.getIdProduto() == idProduto) {
                    produto = p;
                    break;
                }
            }

            if (produto == null) {
                System.out.println("Produto não encontrado!");
                continue;
            }

            System.out.print("Digite a quantidade do produto: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            ItemPedido itemPedido = new ItemPedido(pedido.getItens().size() + 1, pedido, produto, quantidade);
            pedido.adicionarItem(itemPedido);

            System.out.print("Deseja adicionar outro item? (S/N): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("N")) {
                adicionarItens = false;
            }
        }

        sistemaVenda.adicionarPedido(pedido);
        sistemaVenda.adicionarRelatorio("Novo pedido: " + pedido);

        System.out.println("Pedido adicionado com sucesso!");
    }

    private static void atualizarProduto() {
        System.out.println("\n### Atualizar Produto ###");
        System.out.print("Digite o ID do Produto a ser atualizado: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha após o ID
        Produto produtoAtualizado = null;
        for (Produto p : sistemaVenda.listarProdutos()) {
            if (p.getIdProduto() == idProduto) {
                produtoAtualizado = p;
                break;
            }
        }

        if (produtoAtualizado == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.print("Digite o novo preço do Produto: ");
        double novoPreco = scanner.nextDouble();
        System.out.print("Digite o novo estoque do Produto: ");
        int novoEstoque = scanner.nextInt();

        Produto novoProduto = new Produto(produtoAtualizado.getIdProduto(), produtoAtualizado.getNome(),
                produtoAtualizado.getDescricao(), novoPreco, novoEstoque);
        sistemaVenda.atualizarProduto(novoProduto);
        sistemaVenda.adicionarRelatorio("Produto atualizado: " + novoProduto);

        System.out.println("Produto atualizado com sucesso!");
    }

    private static void removerProduto() {
        System.out.println("\n### Remover Produto ###");
        System.out.print("Digite o ID do Produto a ser removido: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha após o ID

        Optional<Produto> produtoEncontrado = sistemaVenda.encontrarProduto(idProduto);
        sistemaVenda.adicionarRelatorio("Produto removido: " + produtoEncontrado);
        sistemaVenda.removerProduto(idProduto);

        System.out.println("Produto removido com sucesso!");
    }

    private static void listarDados() {
        System.out.println("\n### Listar Dados ###");
        System.out.println("Clientes cadastrados:");
        for (Cliente c : sistemaVenda.listarClientes()) {
            System.out.println(c);
        }

        System.out.println("\nProdutos cadastrados:");
        for (Produto p : sistemaVenda.listarProdutos()) {
            System.out.println(p);
        }

        System.out.println("\nPedidos cadastrados:");
        for (Pedido p : sistemaVenda.listarPedidos()) {
            System.out.println(p);
        }
    }

    private static void visualizarRelatorio() {
        System.out.println("\n### Relatório de Mudanças ###");
        List<String> relatorio = sistemaVenda.getRelatorio();

        for (String r : relatorio) {
            System.out.println(r);
        }
    }
}
