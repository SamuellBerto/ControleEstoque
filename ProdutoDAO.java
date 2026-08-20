import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDAO {
    public static void cadastrar(Produto produto) {
        String sql = "INSERT INTO produtos (nome, quantidade, preco) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());

            stmt.executeUpdate();
            System.out.println("Produto salvo no banco com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }

    }
}