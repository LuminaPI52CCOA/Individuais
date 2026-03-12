package school.sptech.projetoindividual.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoindividual.Model.Aluno;
import school.sptech.projetoindividual.Model.Funcionario;
import school.sptech.projetoindividual.Model.Login;
import school.sptech.projetoindividual.Repository.FuncionarioRepository;
import school.sptech.projetoindividual.Service.ValidaçãoCadastro;

@CrossOrigin
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final ValidaçãoCadastro service;
    private final FuncionarioRepository repositorio;

    public FuncionarioController(ValidaçãoCadastro service, FuncionarioRepository repositorio) {
        this.service = service;
        this.repositorio = repositorio;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Funcionario> cadastro (@RequestBody Funcionario f){

    service.cadastrarFuncionario(f.getNome(), f.getEmail(), f.getSenha(), f.getCpf(),
            f.getSexo(), f.getNomeSocial(), f.getNacionalidade(), f.getDtNasc(), f.getTurno(), f.getMetaMensal(), f.getCargo());

    return ResponseEntity.status(201).body(f);
    }

    @GetMapping("/buscarUsuario/{email}")
    public ResponseEntity<Funcionario> selectUsuario(@PathVariable String email){
        Funcionario resposta = repositorio.retornoUsuario(email);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/login")
    public ResponseEntity<Funcionario> login(@RequestBody Login usuario) {

        Funcionario usuarioEncontrado = repositorio.login(usuario.getEmail(), usuario.getSenha());

        if (usuarioEncontrado == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(usuarioEncontrado);
    }

}
