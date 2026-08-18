import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Produto> estoque = new ArrayList<>();
        int opcao;

        do {
            System.out.println("\n=== Controle de Estoque ===");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Atualizar quantidade");
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

                    estoque.add(new Produto(nome, quantidade, preco));
                    System.out.println("Produto cadastrado com sucesso!");
                }
                case 2 -> {
                    System.out.println("\n--- Produtos cadastrados ---");
                    for (Produto p : estoque) {
                        System.out.println(p.getNome() + " | Qtd: " + p.getQuantidade() + " | Preço: R$" + p.getPreco());
                    }
                }
                case 3 -> System.out.println("Você escolheu atualizar quantidade.");
                case 4 -> {
                    System.out.println("\n--- Valor total por Produto ---");
                    double total = 0;
                    for (Produto p : estoque) {
                        double valorProduto = p.getValorTotal();
                        System.out.println(p.getNome () + " : " + p.getQuantidade() + " x R$" + p.getPreco () + " = R$" + valorProduto);
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