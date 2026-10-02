import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start (Stage palco) {
        ConexaoBanco.criarTabela();

        Button btnCadastrar = new Button("Cadastrar Produto");
        Button btnListar = new Button("Listar Produtos");
        Button btnAtualizar = new Button("Atualizar Produto");
        Button btnExcluir = new Button("Excluir Produto");
        Button btnCalcularTotal = new Button("Calcular Valor Total do Estoque");
        Button btnCustoProducao = new Button("Calcular Custo de Produção");
        Button btnHistorico = new Button("Exibir Histórico de Produção");

        btnCadastrar.setOnAction(e -> System.out.println("Clicou em Cadastrar"));
        btnListar.setOnAction(e  -> System.out.println("Clicou em Listar"));
        btnAtualizar.setOnAction(e -> System.out.println("Clicou em Atualizar"));
        btnExcluir.setOnAction(e -> System.out.println("Clicou em Excluir"));
        btnCalcularTotal.setOnAction(e -> System.out.println("Clicou em Calcular  Estoque"));
        btnCustoProducao.setOnAction(e -> System.out.println("Clicou em Calcular Produção"));
        btnHistorico.setOnAction(e -> System.out.println("Clicou em Exibir Histórico"));

        VBox layout =new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                btnCadastrar,
                btnListar,
                btnAtualizar,
                btnExcluir,
                btnCalcularTotal,
                btnCustoProducao,
                btnHistorico
        );

        Scene cena = new Scene(layout, 300, 400);
        palco.setTitle("Controle de Estoque");
        palco.setScene(cena);
        palco.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}