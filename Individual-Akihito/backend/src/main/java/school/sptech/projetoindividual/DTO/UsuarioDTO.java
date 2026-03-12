package school.sptech.projetoindividual.DTO;

import java.sql.Date;

public class UsuarioDTO {
    private int id;
    private String nome;
    private int idade;
    private Date dataCadastro;
    private String tipo;
    private String deckFavorito;
    private String comentario;
    private String raridade;

    public UsuarioDTO() {
    }

    public UsuarioDTO( String nome, int idade, Date dataCadastro, String tipo,
                       String deckFavorito, String comentario, String raridade) {
        this.nome = nome;
        this.idade = idade;
        this.dataCadastro = dataCadastro;
        this.tipo = tipo;
        this.deckFavorito = deckFavorito;
        this.comentario = comentario;
        this.raridade = raridade;
    }

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDeckFavorito() {
        return deckFavorito;
    }

    public void setDeckFavorito(String deckFavorito) {
        this.deckFavorito = deckFavorito;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getRaridade() {
        return raridade;
    }

    public void setRaridade(String raridade) {
        this.raridade = raridade;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", dataCadastro=" + dataCadastro +
                ", tipo='" + tipo + '\'' +
                ", deckFavorito='" + deckFavorito + '\'' +
                ", comentario='" + comentario + '\'' +
                ", raridade='" + raridade + '\'' +
                '}';
    }
}
