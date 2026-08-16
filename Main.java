import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
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

            switch (opcao) {
                case 1 -> System.out.println("Cadastrar produto");
                case 2 -> System.out.println("Listar produtos");
                case 3 -> System.out.println("Atualizar quantidade");
                case 4 -> System.out.println("Calcular valor total do estoque");
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();

    }
}