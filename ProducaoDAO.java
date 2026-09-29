import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProducaoDAO {

    public static void salvarProducao(String nomeProduto, ArrayList<ItemProducao> itens, String data) {
        double custoTotal = 0;
        for (ItemProducao item : itens) {
            custoTotal += item.getValor();

        }
        String sqlProducao = "INSERT INTO producoes (nome_produto, custo_total, data) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt= conexao.prepareStatement(sqlProducao, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, nomeProduto);
                stmt.setDouble(2, custoTotal);
                stmt.setString(3, data);
                stmt.executeUpdate();

                ResultSet chavesGeradas = stmt.getGeneratedKeys();
                int producaoId = 0;
                if (chavesGeradas.next()) {
                    producaoId = chavesGeradas.getInt(1);

                }

                String sqlItem = "INSERT INTO itens_producao (producao_id, nome_item, valor) VALUES (?, ?, ?)";
                try (PreparedStatement stmtItem = conexao.prepareStatement(sqlItem)) {
                    for (ItemProducao item : itens) {
                        stmtItem.setInt(1, producaoId);
                        stmtItem.setString(2, item.getNomeItem());
                        stmtItem.setDouble(3, item.getValor());
                        stmtItem.executeUpdate();
                    }
                }

                System.out.println("Produção salva com sucesso! Custo total: R$ " + String.format("%.2f" , custoTotal));

        } catch (SQLException e) {
            System.out.println("Erro ao salvar produção: " + e.getMessage());
        }
    }
        
}
