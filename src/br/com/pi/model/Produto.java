package br.com.pi.model;

public class Produto {
    private int id;
    private String nome;
    private String codigo;
    private double preco;
    private String categoria;
    private String tipo;
    private boolean disponivel;

    // Construtor vazio
    public Produto() {}

    // Construtor completo
    public Produto(int id, String nome, String codigo, double preco, String categoria, String tipo, boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.categoria = categoria;
        this.tipo = tipo;
        this.disponivel = disponivel;
    }

    // Construtor sem ID (para inserções)
    public Produto(String nome, String codigo, double preco, String categoria, String tipo, boolean disponivel) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.categoria = categoria;
        this.tipo = tipo;
        this.disponivel = disponivel;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                ", preco=" + preco +
                ", categoria='" + categoria + '\'' +
                ", tipo='" + tipo + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }
}

