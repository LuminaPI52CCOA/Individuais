package school.sptech.projetoindividual.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoindividual.model.Usuarios;
import school.sptech.projetoindividual.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UsuariosController {

    private final UsuarioService service;

    public UsuariosController(UsuarioService service){
        this.service = service;
    }



    @PostMapping
    public ResponseEntity<String> cadastro(@RequestBody Usuarios usuario){
        service.salvar(usuario);

        return ResponseEntity.status(201).body("User created!");
    }
}
