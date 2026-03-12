package school.sptech.projetoindividual.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoindividual.Entity.Filme;
import school.sptech.projetoindividual.service.FilmeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/filmes")
@CrossOrigin(origins = "*")
public class FilmeController {
    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping
    public ResponseEntity<Void> criarFilme(@RequestBody CriarFilmeDTO body) {
        var filmeId = filmeService.criarFilme(body);
        return ResponseEntity.created(URI.create("/filmes/" + filmeId.toString())).build();
    }

    @GetMapping("/{filmeId}")
    public ResponseEntity<Filme> getFilmeById(@PathVariable String filmeId) {
        var filmes = filmeService.getFilmeById(filmeId);
        if (filmes.isPresent()) {
            return ResponseEntity.ok(filmes.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listFilmes() {
        var filmes = filmeService.listarFilmes();
        return ResponseEntity.ok(filmes);
    }

    @DeleteMapping("/{filmeId}")
    public ResponseEntity<Void> deletarFilme(@PathVariable String filmeId) {
        var filme = filmeService.getFilmeById(filmeId);
        filmeService.deletarFilme(Integer.valueOf(filmeId));
        return ResponseEntity.noContent().build();
    }
}
