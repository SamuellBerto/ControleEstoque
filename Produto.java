public class Produto {
    // Atributos (características do produto)
    private String nome;
    private int quantidade;
    private double preco;

    // Construtor: usado para criar um novo produto já com os dados
    public Produto(String nome, int quantidade, double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Getters e Setters: métodos para ler e alterar os atributos
    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public double getValorTotal() {
        return quantidade * preco;
    }
}