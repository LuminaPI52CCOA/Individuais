package school.sptech.projetoindividual.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoindividual.Entity.Usuario;
import school.sptech.projetoindividual.service.UsuarioService;

import java.util.List;

//Endpoints
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Integer> criarUsuario(@RequestBody CriarUsuarioDTO body) {
        Integer usuarioSave  =  usuarioService.criarUsuario(body);
        return ResponseEntity.status(201).body(usuarioSave);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario loginDto) {
        var usuarioOpt = usuarioService.autenticar(loginDto.getEmail(), loginDto.getSenha());

        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(usuarioOpt.get());
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String usuarioId) {
        var usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listUsuarios() {
        var usuarios = usuarioService.listUsuarios();
        return ResponseEntity.ok(usuarios);
    }
    @PutMapping("/{usuarioId}")
    public ResponseEntity<Void> updateUsuario(@PathVariable("usuarioId") String usuarioId,
                                              @RequestBody AtualizarUsuarioDTO atualizarUsuarioDTO) {
        var usuarioOpt = usuarioService.getUsuarioById(usuarioId);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.updateUsuario(usuarioId, atualizarUsuarioDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String usuarioId) {
        var usuario = usuarioService.getUsuarioById(usuarioId);
            usuarioService.deleteUsuario(usuarioId);
            return ResponseEntity.noContent().build();
    }
}
