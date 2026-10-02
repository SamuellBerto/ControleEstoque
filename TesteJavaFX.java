import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TesteJavaFX extends Application {

    @Override
    public void start(Stage palco) {
        Button botao = new Button("Funcionou!");
        botao.setOnAction(e -> System.out.println("JavaFX está funcionando!"));

        StackPane raiz = new StackPane(botao);
        Scene cena = new Scene(raiz, 300, 200);

        palco.setTitle("Teste JavaFX");
        palco.setScene(cena);
        palco.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}