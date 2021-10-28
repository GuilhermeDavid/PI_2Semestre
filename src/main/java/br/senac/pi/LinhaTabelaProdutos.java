package br.senac.pi;

public class LinhaTabelaProdutos {
   
    private String nome;
    private String marca;
    private String tipo;
    private int quantidade;
    private double preco;
    private String codigo;

    public LinhaTabelaProdutos(String nome, String marca, String tipo, int quantidade, double preco, String codigo) {
        this.nome = nome;
        this.marca = marca;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.preco = preco;
        this.codigo = codigo;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
