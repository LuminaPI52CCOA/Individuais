package school.sptech.projetoindividual.Repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import school.sptech.projetoindividual.DTO.UsuarioDTO;

import java.sql.Date;
import java.util.List;

@Repository
public class UsuarioRepository {

    private final JdbcTemplate template;

    public UsuarioRepository(JdbcTemplate template){this.template = template;}

    public List<UsuarioDTO> listarTodosUsuarios(){
        String sql = """
                SELECT * FROM usuario;
                """;

    List<UsuarioDTO> usuarios =template.query(sql, new BeanPropertyRowMapper<>(UsuarioDTO.class));

    return usuarios;
    }

    public void adicionarUsuario(String nome, int idade, Date data_cadastro,String raridade,
                                 String tipo, String deck_fav, String comentario){
        String sql = """
                INSERT INTO usuario (nome, idade, data_cadastro,raridade, tipo, deck_favorito, comentario)
                 VALUES (?, ?, ?, ?, ?, ?, ?);
                """;
        template.update(sql, nome, idade, data_cadastro,raridade, tipo, deck_fav, comentario);
    }




}
