package school.sptech.projetoindividual.Repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import school.sptech.projetoindividual.Model.Aluno;
import school.sptech.projetoindividual.Model.Funcionario;
import school.sptech.projetoindividual.Model.Pessoa;

import java.util.Date;
import java.util.List;

@Repository
public class AlunoRepository {

    private final JdbcTemplate jdbcTemplate;

    public AlunoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }



    public void cadastrarBanco (String nome, String email, String senha, String cpf, String sexo, String nomeSocial, String nacionalidade, Date dtNasc, int curso){

        String sql = "insert into aluno (nome, email, senha, cpf, sexo, nomeSocial, nacionalidade, dtNasc, fkcurso )" +
                " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, nome, email, senha, cpf, sexo, nomeSocial, nacionalidade, dtNasc, curso);
    }

    public Aluno retornoUsuario(String email){

        String select = "select nome from aluno where email = ?";

        List<Aluno> lista = jdbcTemplate.query(
                select,
                new BeanPropertyRowMapper<>(Aluno.class),
                email
        );
        if (lista.isEmpty()) {
            return null;
        }
        return lista.get(0);
    }

    public Aluno login(String email, String senha) {

        String sql = "SELECT * FROM aluno WHERE email = ? AND senha = ?";

        List<Aluno> usuarios = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Aluno aluno = new Aluno();
            aluno.setNome(rs.getString("nome"));
            aluno.setEmail(rs.getString("email"));
            aluno.setSenha(rs.getString("senha"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setSexo(rs.getString("sexo"));
            aluno.setNomeSocial(rs.getString("nomeSocial"));
            aluno.setNacionalidade(rs.getString("nacionalidade"));
            aluno.setDtNasc(rs.getDate("dtNasc"));
            aluno.setCurso(rs.getInt("fkcurso"));
            return aluno;
        }, email, senha);

        if (usuarios.isEmpty()) {
            return null;
        }

        return usuarios.get(0);
    }
}
