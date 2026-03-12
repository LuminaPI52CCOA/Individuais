package school.sptech.projetoindividual.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoindividual.DTO.RaridadeDTO;
import school.sptech.projetoindividual.DTO.UsuarioDTO;
import school.sptech.projetoindividual.Repository.RaridadeRepository;
import school.sptech.projetoindividual.Repository.UsuarioRepository;

import java.util.List;


@RestController
@RequestMapping("/raridades")
@CrossOrigin(origins = "*", maxAge = 3600)

public class RaridadeController {
    private final JdbcTemplate template;
    private final RaridadeRepository templateRepository;

    public RaridadeController(JdbcTemplate template, RaridadeRepository templateRepository) {
            this.template = template;
            this.templateRepository = templateRepository;
    }

    @PostMapping("/{raridade}")
    public ResponseEntity<String> adicionarRarirade(@PathVariable String raridade){
        if(raridade == null ||raridade.isEmpty()){return ResponseEntity.status(400).build();};
        templateRepository.adicionarRaridade(raridade);

        return ResponseEntity.status(201).body(raridade);
    }

    @GetMapping
    public ResponseEntity<List<RaridadeDTO>> listarRaridade(){

        List<RaridadeDTO> raridade =templateRepository.listarTodasRaridades();

        if(raridade.isEmpty()){
            return ResponseEntity.status(204).build();
        }return ResponseEntity.status(200).body(raridade);
    }


}
