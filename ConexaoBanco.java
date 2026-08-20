import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBanco {
    private static final String URL = "jdbc:sqlite:estoque.db";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e ) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
            return null;
        }
    }
    public static void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS produtos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT NOT NULL," +
                    "quantidade INTEGER NOT NULL," +
                    "preco REAL NOT NULL" +
                    ")";

        try (Connection conexao = conectar();
        Statement stmt = conexao.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela verificada/criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}


