import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProducaoDAO {

    public static void salvarProducao(String nomeProduto, ArrayList<ItemProducao> itens, int quantidadeProduzida, String data) {
        double custoTotal = 0;
        for (ItemProducao item : itens) {
            custoTotal += item.getValor();
        }

        double custoPorUnidade = custoTotal / quantidadeProduzida;

        String sqlProducao = "INSERT INTO producoes (nome_produto, quantidade_produzida, custo_total, data) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sqlProducao, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, nomeProduto);
                stmt.setInt(2, quantidadeProduzida);
                stmt.setDouble(3, custoTotal);
                stmt.setString(4, data);
                stmt.executeUpdate();

                ResultSet chavesGeradas = stmt.getGeneratedKeys();
                int producaoId = 0;
                if (chavesGeradas.next()) {
                    producaoId = chavesGeradas.getInt(1);
                }

                String sqlItem = "INSERT INTO itens_producao (producao_id, nome_item, proporcao, valor) VALUES (?, ?, ? , ?)";
                try (PreparedStatement stmtItem = conexao.prepareStatement(sqlItem)) {
                    for (ItemProducao item : itens) {
                        stmtItem.setInt(1, producaoId);
                        stmtItem.setString(2, item.getNomeItem());
                        stmtItem.setString(3, item.getProporcao());
                        stmtItem.setDouble(4, item.getValor());
                        stmtItem.executeUpdate();
                    }
                }

                System.out.printf("%nProdução salva com sucesso!%n");
                System.out.printf("Custo total da produção: R$%.2f%n", custoTotal);
                System.out.printf("Você produziu %d unidade(s) de \"%s\".%n", quantidadeProduzida, nomeProduto);
                System.out.printf("Custo por unidade: R$%.2f%n", custoPorUnidade);

        } catch (SQLException e) {
            System.out.println("Erro ao salvar produção: " + e.getMessage());
        }
    }
    public static void listarProducoes() {
        String sql = "SELECT * FROM producoes ORDER BY data DESC";

        try (Connection conexao =  ConexaoBanco.conectar();
            PreparedStatement stmt =conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            boolean encontrouAlgum = false;
            while (rs.next()) {
                encontrouAlgum = true;
                String nomeProduto = rs.getString("nome_produto");
                int quantidadeProduzida = rs.getInt("quantidade_produzida");
                double custoTotal = rs.getDouble("custo_total");
                String data = rs.getString("data");
                double custoPorUnidade = custoTotal / quantidadeProduzida;

                System.out.printf("%n%s (produzido em %s)%n", nomeProduto, data);
                System.out.printf("Quantidade: %d unidade(s) | Custo total: R$%.2f | Custo por unidade: R$%.2f%n", quantidadeProduzida, custoTotal, custoPorUnidade);
            }
            if (!encontrouAlgum) {
                System.out.println("Nenhuma produção encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produções: " + e.getMessage());
        }
    }
}