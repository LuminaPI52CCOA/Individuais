package school.sptech.projetoindividual.Model;

public class Cursos {

    private int id;
    private String nome, turno;
    private int tempo;

    public Cursos(String nome, String turno, int tempo) {
        this.nome = nome;
        this.turno = turno;
        this.tempo = tempo;
    }

    public Cursos(){}

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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
