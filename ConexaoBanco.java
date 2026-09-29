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
        String sqlProdutos = "CREATE TABLE IF NOT EXISTS produtos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "quantidade INTEGER NOT NULL," +
                "preco REAL NOT NULL" +
                ");";
        String sqlProducoes = "CREATE TABLE IF NOT EXISTS producoes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome_produto TEXT NOT NULL," +
                "quantidade_produzida INTEGER NOT NULL," +
                "custo_total REAL NOT NULL," +
                "data TEXT NOT NULL" +
                ");";
        String sqlItensProducao = "CREATE TABLE IF NOT EXISTS itens_producao (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "producao_id INTEGER NOT NULL," +
                "nome_item TEXT NOT NULL," +
                "valor REAL NOT NULL," +
                "FOREIGN KEY (producao_id) REFERENCES producoes(id)" +
                ")";
        try (Connection conexao = conectar();
           Statement stmt = conexao.createStatement()) {
            stmt.execute(sqlProdutos);
            stmt.execute(sqlProducoes);
            stmt.execute(sqlItensProducao);
            System.out.println("Tabelas criadas com sucesso!");
           } catch (SQLException e) {
                System.out.println("Erro ao criar tabelas: " + e.getMessage());
           }
        }

}


