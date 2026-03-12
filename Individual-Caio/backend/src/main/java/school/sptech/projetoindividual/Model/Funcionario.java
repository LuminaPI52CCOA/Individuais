package school.sptech.projetoindividual.Model;

import java.util.Date;

public class Funcionario extends Pessoa{

    private String turno;
    private Integer  fkcargo;
    private Double metaMensal;

    public Funcionario(String nome, String email, String senha, String cpf, String sexo, String nomeSocial, String nacionalidade, Date dtNasc, String turno, Double metaMensal, Integer cargo) {
        super(nome, email, senha, cpf, sexo, nomeSocial, nacionalidade, dtNasc);
        this.turno = turno;
        this.metaMensal = metaMensal;
        this.fkcargo = cargo;
    }
    public Funcionario() {
        super();
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Double getMetaMensal() {
        return metaMensal;
    }

    public void setMetaMensal(Double metaMensal) {
        this.metaMensal = metaMensal;
    }

    public Integer getCargo() {
        return fkcargo;
    }

    public void setCargo(Integer cargo) {
        this.fkcargo = cargo;
    }
}
