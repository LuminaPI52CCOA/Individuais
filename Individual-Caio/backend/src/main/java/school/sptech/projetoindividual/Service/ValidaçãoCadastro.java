package school.sptech.projetoindividual.Service;

import org.springframework.stereotype.Service;
import school.sptech.projetoindividual.Repository.AlunoRepository;
import school.sptech.projetoindividual.Repository.FuncionarioRepository;
import school.sptech.projetoindividual.RunException.RetornoApi;

import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class ValidaçãoCadastro {

    private final AlunoRepository repositorio;
    private final FuncionarioRepository repositorioFun;

    public ValidaçãoCadastro(AlunoRepository repositorio, FuncionarioRepository repositorioFun) {
        this.repositorio = repositorio;
        this.repositorioFun = repositorioFun;
    }


    public String validarCadastroAluno (String nome, String email, String senha, String cpf, String sexo, String nomeSocial, String nacionalidade, Date dtNasc, int curso){

        Pattern patternEmail = Pattern.compile("^[a-z.]+@+bibliotech.com$");
        Pattern patternSenha = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$");
        Matcher a, b;
        a = patternEmail.matcher(email);
        b = patternSenha.matcher(senha);


        if (nome == null || nome.isBlank()) {
            throw new RetornoApi("Nome é obrigatório");
        }
        if (nome.length() < 3) {
            throw new RetornoApi("Nome deve ter pelo menos 3 caracteres");
        }
        if (email == null || email.isBlank()) {
            throw new RetornoApi("Email é obrigatório");
        }
        if (!a.matches()) {
            throw new RetornoApi("Email inválido");
        }
        if (senha == null || senha.isBlank()) {
            throw new RetornoApi("Senha é obrigatória");
        }
        if (!b.matches()) {
            throw new RetornoApi("Senha Incorreta");
        }
        if (cpf == null || cpf.isBlank()){
            throw new RetornoApi("Cpf é obrigatório");
        }
        if (sexo == null || sexo.isBlank()) {
            throw new RetornoApi("Sexo é obrigatório");
        }
        if(nacionalidade == null || nacionalidade.isBlank()){
            throw new RetornoApi("Nacionalidade é obrigatório");
        }
        if(dtNasc == null ){
            throw new RetornoApi("Data de Nascimento é obrigatório");
        }if(dtNasc.after(new Date())){
            throw new RetornoApi("Data de nascimento não pode ser uma data no futuro!");
        }

        repositorio.cadastrarBanco(nome, email, senha, cpf, sexo, nomeSocial, nacionalidade, dtNasc, curso);
        return "Concluído";
    }



    public String cadastrarFuncionario(String nome, String email, String senha, String cpf, String sexo, String nomeSocial,
                                       String nacionalidade, Date dtNasc, String turno, Double metaMensal, Integer cargo){

        Pattern patternEmail = Pattern.compile("^[a-z.]+@+bibliotech.com$");
        Pattern patternSenha = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$");
        Matcher a, b;
        a = patternEmail.matcher(email);
        b = patternSenha.matcher(senha);


        if (nome == null || nome.isBlank()) {
            throw new RetornoApi("Nome é obrigatório");
        }
        if (nome.length() < 3) {
            throw new RetornoApi("Nome deve ter pelo menos 3 caracteres");
        }
        if (email == null || email.isBlank()) {
            throw new RetornoApi("Email é obrigatório");
        }
        if (!a.matches()) {
            throw new RetornoApi("Email inválido");
        }
        if (senha == null || senha.isBlank()) {
            throw new RetornoApi("Senha é obrigatória");
        }
        if (!b.matches()) {
            throw new RetornoApi("Senha Incorreta");
        }
        if (cpf == null || cpf.isBlank()){
            throw new RetornoApi("Cpf é obrigatório");
        }
        if (sexo == null || sexo.isBlank()) {
            throw new RetornoApi("Sexo é obrigatório");
        }
        if(nacionalidade == null || nacionalidade.isBlank()){
            throw new RetornoApi("Nacionalidade é obrigatório");
        }
        if(dtNasc == null ){
            throw new RetornoApi("Data de Nascimento é obrigatório");
        }if(dtNasc.after(new Date())){
            throw new RetornoApi("Data de nascimento não pode ser uma data no futuro!");
        }
        if (turno == null || turno.isBlank()){
            throw new RetornoApi("Turno é obrigatório");
        }
        if (cargo == null){
            throw new RetornoApi("Cargo é obrigatório");
        }
        if (metaMensal == null){
            throw new RetornoApi("Meta mensal é obrigatória");
        }
        if (metaMensal < 0 || cargo < 0){
            throw new RetornoApi("O valor não pode ser menor que 0");
        }

        repositorioFun.cadastro(nome, email, senha, cpf, sexo, nomeSocial, nacionalidade, dtNasc, turno, metaMensal, cargo);
        return "Concluído!";
    }


}
