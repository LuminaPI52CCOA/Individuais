package school.sptech.projetoindividual.Repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import school.sptech.projetoindividual.DTO.RaridadeDTO;
import school.sptech.projetoindividual.DTO.UsuarioDTO;

import java.util.List;


@Repository
public class RaridadeRepository {

    private final JdbcTemplate template;

    public RaridadeRepository(JdbcTemplate template){this.template = template;}

    public void adicionarRaridade(String raridade){
        String sql = """
                INSERT INTO raridade (raridade) VALUES (?);
                """;
        template.update(sql, raridade);
    }

    public List<RaridadeDTO> listarTodasRaridades(){
        String sql = """
                SELECT * FROM raridade;
                """;

         List<RaridadeDTO> raridade =template.query(sql, new BeanPropertyRowMapper<>(RaridadeDTO.class));

        return raridade;
    }



}
