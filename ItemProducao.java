public class ItemProducao {
    private String nomeItem;
    private String proporcao;
    private double valor;

    public ItemProducao(String nomeItem, String proporcao, double valor) {
        this.nomeItem = nomeItem;
        this.proporcao = proporcao;
        this.valor = valor;
        
    }
    
    public String getNomeItem() {
        return nomeItem;
    }
    public String getProporcao() {
        return proporcao;
    }

    public double getValor() {
        return valor;
    }
}
