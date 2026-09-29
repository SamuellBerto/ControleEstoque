public class ItemProducao {
    private String nomeItem;
    private double valor;

    public ItemProducao(String nomeItem, double valor) {
        this.nomeItem = nomeItem;
        this.valor = valor;
       
    }
    
    public String getNomeItem() {
        return nomeItem;
    }

    public double getValor() {
        return valor;
    }
}
