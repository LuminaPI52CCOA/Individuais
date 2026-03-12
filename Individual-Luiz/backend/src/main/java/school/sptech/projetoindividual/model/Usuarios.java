package school.sptech.projetoindividual.model;

import java.util.Date;

public class Usuarios {
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private Date dtNascimento;
    private String clube;

    public Usuarios(){

    }

    public Usuarios(String nome, String sobrenome, String email, String senha, Date dtNascimento, String clube) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dtNascimento = dtNascimento;
        this.email = email;
        this.senha = senha;
        this.clube = clube;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getClubes() {
        return clube;
    }

    public void setClubes(String clube) {
        this.clube = clube;
    }
}
