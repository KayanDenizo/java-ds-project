package br.com.pi.model;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String sexo;
    private String uf;
    private boolean newsletter;

    // Construtor vazio
    public Cliente() {}

    // Construtor completo
    public Cliente(int id, String nome, String email, String cpf, String sexo, String uf, boolean newsletter) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.sexo = sexo;
        this.uf = uf;
        this.newsletter = newsletter;
    }

    // Construtor sem ID (para inserções)
    public Cliente(String nome, String email, String cpf, String sexo, String uf, boolean newsletter) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.sexo = sexo;
        this.uf = uf;
        this.newsletter = newsletter;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", sexo='" + sexo + '\'' +
                ", uf='" + uf + '\'' +
                ", newsletter=" + newsletter +
                '}';
    }
}
