package school.sptech.projetoindividual.Repository;

import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import school.sptech.projetoindividual.Model.Livros;

import java.util.List;

@Repository
public class   LivrosRepository {

    private final JdbcTemplate jdbcTemplate;

    public LivrosRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void cadastrar (String titulo, String autor, int genero) {
        String sql = "INSERT INTO livros (titulos, autor, fkCategoria) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, titulo, autor, genero);
    }

    public List<Livros> listarLivros() {
        String sql = """
                   SELECT\s
                       l.id,
                       l.titulos AS titulo,
                       l.autor,
                       c.tipo AS genero
                   FROM livros l
                   JOIN categoria c ON l.fkCategoria = c.id;
                """;
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Livros.class));
        }

        public Integer existe(Integer id){
           String sql = "select count(id) from livros where id = ?";
           Integer existe = jdbcTemplate.queryForObject(sql, Integer.class, id);

           return existe;
        }

        public Integer deletarLivro(Integer deletar){
            String sql = "DELETE FROM livros WHERE id = ?";
            Integer quantidade = jdbcTemplate.update(sql, deletar);

            return quantidade;
        }


}

