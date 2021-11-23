package br.senac.pi;

public class LinhaTabelaVenda {
    private int id;
    private String produto;
    private int quant;
    private float preco;

    public LinhaTabelaVenda(int id, String produto, int quant, float preco) {
        this.id = id;
        this.produto = produto;
        this.quant = quant;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    
}
