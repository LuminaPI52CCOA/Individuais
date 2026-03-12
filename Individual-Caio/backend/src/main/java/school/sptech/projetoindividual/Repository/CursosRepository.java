package school.sptech.projetoindividual.Repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import school.sptech.projetoindividual.Model.Cursos;

@Repository
public class CursosRepository {

    private final JdbcTemplate jdbcTemplate;

    public CursosRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void cadastrar (String nome, String turno, int tempo){
        String sql = "insert into cursos (nome, turno, tempo) values(?, ?, ?)";
        jdbcTemplate.update(sql, nome, turno, tempo);
    }

    public void selectCurso (String nome){
        String sql = "select * from curso like '%" + nome +"%' ";
        jdbcTemplate.query(sql,  new BeanPropertyRowMapper<>(Cursos.class), nome);
    }
}