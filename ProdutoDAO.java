import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.Normalizer;

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

    public static java.util.ArrayList<Produto> listar() {
        java.util.ArrayList<Produto> produtos = new java.util.ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                java.sql.ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                int quantidade = rs.getInt("quantidade");
                double preco = rs.getDouble("preco");

                produtos.add(new Produto(nome, quantidade, preco));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return produtos;
    }

    public static void atualizarProduto(String nome, int novaQuantidade, double novoPreco) {
        String nomeReal = buscarNomeReal(nome);

        if (nomeReal == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        String sql = "UPDATE produtos SET quantidade = ?, preco = ? WHERE nome = ?";

        try (Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, novaQuantidade);
            stmt.setDouble(2, novoPreco);
            stmt.setString(3, nomeReal);

            stmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    public static void excluir(String nome) {
        String nomeReal = buscarNomeReal(nome);

        if (nomeReal == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        String sql = "DELETE FROM produtos WHERE nome = ?";

        try (Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nomeReal);
            stmt.executeUpdate();
            System.out.println("Produto excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir produto: " + e.getMessage());
        }
    }

    private static String normalizar(String texto) {
        String semAcento = Normalizer.normalize(texto, Normalizer.Form.NFD)
                                      .replaceAll("[^\\p{ASCII}]", "");
        return semAcento.toUpperCase();
    }

    private static String buscarNomeReal(String nomeDigitado) {
        String nomeNormalizado = normalizar(nomeDigitado);

        for (Produto p : listar()) {
            if (normalizar(p.getNome()).equals(nomeNormalizado)) {
                return p.getNome();
            }
        }
        return null;
    }
}