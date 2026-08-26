import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ConexaoBanco.criarTabela();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Produto> estoque = new ArrayList<>();
        int opcao;

        do {
            System.out.println("\n=== Controle de Estoque ===");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Atualizar produto (quantidade e preço)");
            System.out.println("4 - Calcular valor total do estoque");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa o "enter" que sobrou no buffer

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Preço unitário: ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine();

                    Produto novoProduto = new Produto(nome, quantidade, preco);
                    ProdutoDAO.cadastrar(novoProduto);

                }
                case 2 -> {
                    System.out.println("\n--- Produtos cadastradors ---");
                    ArrayList<Produto> produtosDoBanco = ProdutoDAO.listar();
                    for (Produto p : produtosDoBanco) {
                        System.out
                                .println(p.getNome() + " | Qtd: " + p.getQuantidade() + " | Preço: R$" + p.getPreco());
                    }
                }
                case 3 -> {
                    System.out.print("Nome do produto a atualizar: ");
                    String nomeProduto = scanner.nextLine();
                    System.out.print("Nova quantidade: ");
                    int novaQuantidade = scanner.nextInt();
                    System.out.print("Novo preço: ");
                    double novoPreco = scanner.nextDouble();
                    scanner.nextLine();

                    ProdutoDAO.atualizarProduto(nomeProduto, novaQuantidade, novoPreco);
                }
                case 4 -> {
                    System.out.println("\n--- Valor total por Produto ---");
                    double total = 0;
                    for (Produto p : estoque) {
                        double valorProduto = p.getValorTotal();
                        System.out.println(p.getNome() + " : " + p.getQuantidade() + " x R$" + p.getPreco() + " = R$"
                                + valorProduto);
                        total += valorProduto;
                    }
                    System.out.println("\n Valor Total do Estoque: R$" + total);
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}