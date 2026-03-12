package school.sptech.projetoindividual.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "filme")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filme")
    private Integer id;

    @Column(name = "titulo_filme")
    private String titulo;

    @Column(name = "genero_filme")
    private String genero;

    @Column(name = "class_ind")
    private String classInd;

    public Filme() {
    }

    public Filme(Integer id, String titulo, String genero, String classInd) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.classInd = classInd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getClassInd() {
        return classInd;
    }
}
