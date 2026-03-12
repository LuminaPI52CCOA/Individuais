package school.sptech.projetoindividual.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoindividual.Entity.Avaliacao;
import school.sptech.projetoindividual.service.AvaliacaoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {
    private AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> criarAvaliacao(@RequestBody CriarAvaliacaoDTO body) {
        var avaliacaoId = avaliacaoService.criarAvaliacao(body);
        return ResponseEntity.created(URI.create("/avaliacoes/" + avaliacaoId.toString())).build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Avaliacao>> listarAvaliacoes(@PathVariable Integer idUsuario) {
       var avaliacoes = avaliacaoService.listarAvaliacoes(idUsuario);
        if (avaliacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{avaliacaoId}")
    public ResponseEntity<Avaliacao> getAvaliacaoById(@PathVariable String avaliacaoId) {
        var avaliacao =  avaliacaoService.getAvaliacaoById(avaliacaoId);
                if(avaliacao.isPresent()){
                    return ResponseEntity.ok(avaliacao.get());
                } else {
                    return ResponseEntity.notFound().build();
                }
    }

    @PutMapping("/{avaliacaoId}")
    public ResponseEntity<Void> updateAvaliacao(@PathVariable("avaliacaoId") String avaliacaoId,
                                              @RequestBody AtualizarAvaliacaoDTO atualizarAvaliacaoDTO) {
        var avaliacaoOpt = avaliacaoService.getAvaliacaoById(avaliacaoId);
        if (avaliacaoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        avaliacaoService.updateAvaliacao(avaliacaoId, atualizarAvaliacaoDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{avaliacaoId}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable String avaliacaoId) {
        avaliacaoService.deletarAvaliacao(avaliacaoId);
        return ResponseEntity.noContent().build();
    }
}
