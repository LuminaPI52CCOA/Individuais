package school.sptech.projetoindividual.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoindividual.DTO.UsuarioDTO;
import school.sptech.projetoindividual.Repository.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", maxAge = 3600)



public class UsuarioController {

    private final JdbcTemplate template;
    private final UsuarioRepository templateRepository;


    public UsuarioController(JdbcTemplate template, UsuarioRepository templateRepository){
        this.template = template;
        this.templateRepository = templateRepository;
    }



    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuario(){

        List<UsuarioDTO> usuarios =templateRepository.listarTodosUsuarios();

        if(usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }return ResponseEntity.status(201).body(usuarios);
    }

    @PostMapping()
    public ResponseEntity<UsuarioDTO> adicionarUsuario(@RequestBody UsuarioDTO usuarios){

        if(usuarios == null){
            return ResponseEntity.status(400).build();}
        templateRepository.adicionarUsuario(usuarios.getNome(), usuarios.getIdade(), usuarios.getDataCadastro(),
                usuarios.getRaridade(), usuarios.getTipo(), usuarios.getDeckFavorito(), usuarios.getComentario());

        System.out.println(usuarios.toString());

        return ResponseEntity.status(201).body(usuarios);
    }




}
