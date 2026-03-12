package school.sptech.projetoindividual.Model;

import java.util.Date;

public class Pessoa {
    private String nome, email, senha, cpf, sexo, nomeSocial, nacionalidade;
    private Date dtNasc;

    public Pessoa(String nome, String email, String senha, String cpf, String sexo, String nomeSocial, String nacionalidade, Date dtNasc) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.sexo = sexo;
        this.nomeSocial = nomeSocial;
        this.nacionalidade = nacionalidade;
        this.dtNasc = dtNasc;
    }
    public Pessoa() {}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public Date getDtNasc() {
        return dtNasc;
    }
}
