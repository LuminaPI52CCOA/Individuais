package school.sptech.projetoindividual.Service;

import org.springframework.stereotype.Service;
import school.sptech.projetoindividual.Repository.CursosRepository;
import school.sptech.projetoindividual.RunException.RetornoApi;

@Service
public class ValidacaoCurso {

    private final CursosRepository repositorio;

    public ValidacaoCurso(CursosRepository repositorio) {
        this.repositorio = repositorio;
    }

    public String validarCurso(String nome, String turno, int tempo){

        if(nome == null || nome.isBlank()){
            throw new RetornoApi("Nome é obrigatório!");
        }
        if(turno == null || turno.isBlank()){
            throw new RetornoApi("Turno é obrigatório!");
        }

        repositorio.cadastrar(nome, turno, tempo);
        return "Concluido";
    }
}
