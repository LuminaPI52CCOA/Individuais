package school.sptech.projetoindividual;


import java.time.LocalDate;

public class CorinthiansModel {

    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataNascimento;
    private String tipoPlano;
    private Boolean aceitaEmails;
    private String setorPreferidoEstadio;

    public CorinthiansModel() {
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(String tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public Boolean getAceitaEmails() {
        return aceitaEmails;
    }

    public void setAceitaEmails(Boolean aceitaEmails) {
        this.aceitaEmails = aceitaEmails;
    }

    public String getSetorPreferidoEstadio() {
        return setorPreferidoEstadio;
    }

    public void setSetorPreferidoEstadio(String setorPreferidoEstadio) {
        this.setorPreferidoEstadio = setorPreferidoEstadio;
    }
}
