package school.sptech.projetoindividual.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoindividual.Model.Cursos;
import school.sptech.projetoindividual.Service.ValidacaoCurso;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Cursos")
public class CursosController {

    private final ValidacaoCurso service;
    private final JdbcTemplate template;

    public CursosController( ValidacaoCurso service, JdbcTemplate template) {
        this.service = service;
        this.template = template;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Cursos> criarCurso(@RequestBody Cursos cursos){
       service.validarCurso(cursos.getNome(), cursos.getTurno(), cursos.getTempo());
        return ResponseEntity.status(HttpStatus.CREATED).body(cursos);
    }

    @GetMapping
    public ResponseEntity<List<Cursos>> selectCursos(){
        String sql = "SELECT * FROM cursos";
        List<Cursos> cursos = template.query(sql,  new BeanPropertyRowMapper<>(Cursos.class));

        return ResponseEntity.status(200).body(cursos);

    }
}
