package br.senac.pi;

public class LinhaTabelaProdutos {
    private String nome;
    private String marca;
    private String tipo;
    private String quantidade;
    private double preco;

 
   
    
    public LinhaTabelaProdutos(String nome, String marca, String tipo, String quantidade, double preco) {
        this.nome = nome;
        this.marca = marca;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    LinhaTabelaProdutos(String guilherme, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
    
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
}
