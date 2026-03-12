package school.sptech.projetoindividual.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoindividual.Model.Aluno;
import school.sptech.projetoindividual.Model.Funcionario;
import school.sptech.projetoindividual.Model.Login;
import school.sptech.projetoindividual.Repository.AlunoRepository;
import school.sptech.projetoindividual.Service.ValidaçãoCadastro;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final ValidaçãoCadastro service;
    private final AlunoRepository repositorio;

    public AlunoController(ValidaçãoCadastro service, AlunoRepository repositorio) {
        this.service = service;
        this.repositorio = repositorio;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Aluno> cadastrarUsuarioo(@RequestBody Aluno aluno){
        String resposta = service.validarCadastroAluno(aluno.getNome(), aluno.getEmail(), aluno.getSenha(), aluno.getCpf(),
                aluno.getSexo(), aluno.getNomeSocial(), aluno.getNacionalidade(), aluno.getDtNasc(), aluno.getCurso());
        return ResponseEntity.status(201).body(aluno);
    }

    @GetMapping("/buscarUsuario/{email}")
    public ResponseEntity<Aluno> selectUsuario(@PathVariable String email){
        Aluno resposta = repositorio.retornoUsuario(email);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/login")
    public ResponseEntity<Aluno> login(@RequestBody Login login) {

        Aluno usuarioEncontrado =
                repositorio.login(login.getEmail(), login.getSenha());

        if (usuarioEncontrado == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(usuarioEncontrado);
    }
}
