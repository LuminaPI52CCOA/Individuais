package school.sptech.projetoindividual;

import school.sptech.projetoindividual.CorinthiansModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/corinthians")
@CrossOrigin("*") // Para o HTML conseguir acessar
public class CorinthiansController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Endpoint GET para popular o Dropdown (Setores da Neo Química Arena)
    @GetMapping("/setores")
    public List<String> getSetores() {
        return Arrays.asList("Norte", "Sul", "Leste Superior Lateral", "Leste Superior Central", "Leste Inferior Lateral", "Leste Inferior Central", "Oeste Superior", "Oeste Inferior Lateral", "Oeste - Minha Cadeira");
    }

    // Endpoint POST para salvar o cadastro 
    @PostMapping("/cadastrar")
    public ResponseEntity<Void> cadastrar(@RequestBody CorinthiansModel torcedor) {

        // Validação
        if (torcedor.getNome() == null || torcedor.getNome().isEmpty() ||
                torcedor.getCpf() == null || torcedor.getCpf().isEmpty()) {
            return ResponseEntity.status(400).build(); // Bad Request
        }

        // Query para inserir os dados do torcedor no banco de dados
        String sql = "INSERT INTO torcedores (nome, email, cpf, data_nascimento, tipo_plano, aceita_emails, setor_estadio) VALUES (?, ?, ?, ?, ?, ?, ?)";

        int rows = jdbcTemplate.update(sql,
                torcedor.getNome(),
                torcedor.getEmail(),
                torcedor.getCpf(),
                torcedor.getDataNascimento(),
                torcedor.getTipoPlano(),
                torcedor.getAceitaEmails(),
                torcedor.getSetorPreferidoEstadio()
        );

        if (rows > 0) {
            return ResponseEntity.status(201).build(); // Created
        } else {
            return ResponseEntity.status(400).build();
        }
    }


}
