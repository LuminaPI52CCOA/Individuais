package school.sptech.projetoindividual.Model;

import java.util.Date;

public class Aluno extends Pessoa {
    private int fkCurso;

   public Aluno (String nome, String email, String senha, String cpf, String sexo, String nomeSocial, String nacionalidade, Date dtNasc, int fkCurso) {
        super(nome, email, senha, cpf, sexo, nomeSocial, nacionalidade, dtNasc);
       this.fkCurso = fkCurso;
    }
    public Aluno(){

    }

    public int getCurso() {
        return fkCurso;
    }

    public void setCurso(int fkCurso) {
        this.fkCurso = fkCurso;
    }
}
