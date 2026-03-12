package school.sptech.projetoindividual.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_avaliacao")
    private Integer id;

    @Column(name = "nota")
    private Double nota;

    @Column(name = "favorito")
    private Boolean favorito;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "fk_filme")
    private Filme idFilme;

    public Avaliacao() {}

    public Avaliacao(Integer id, Double nota, Boolean favorito,String descricao, Usuario idUsuario, Filme idFilme) {
        this.id = id;
        this.nota = nota;
        this.favorito = favorito;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
        this.idFilme = idFilme;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Filme getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Filme idFilme) {
        this.idFilme = idFilme;
    }
}
