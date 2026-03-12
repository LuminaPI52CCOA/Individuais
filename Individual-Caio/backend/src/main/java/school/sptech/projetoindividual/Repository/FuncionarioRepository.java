package school.sptech.projetoindividual.Repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import school.sptech.projetoindividual.Model.Aluno;
import school.sptech.projetoindividual.Model.Funcionario;

import java.util.Date;
import java.util.List;

@Repository
public class FuncionarioRepository {

    private final JdbcTemplate jdbcTemplate;

    public FuncionarioRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void cadastro(String nome, String email, String senha,
                         String cpf, String sexo, String nomeSocial,
                         String nacionalidade, Date dtNasc, String turno, Double metaMensal, int fkcargo){
        String sql = "insert into funcionario (nome, email, senha, cpf, sexo, nomeSocial," +
                " nacionalidade, dtNasc, turno, metaMensal, fkcargo) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, nome, email, senha, cpf, sexo, nomeSocial, nacionalidade, dtNasc, turno, metaMensal, fkcargo);
    }

    public void selectGerentes ( String email){
        String sql = "select nome, cargo from funcionario where ='" + email +"' ";


    }

    public Funcionario login(String email, String senha) {

        String sql = "SELECT * FROM funcionario WHERE email = ? AND senha = ?";

        List<Funcionario> usuarios = jdbcTemplate.query(
                sql, (rs, rowNum) -> {
                Funcionario f = new Funcionario();
                    f.setNome(rs.getString("nome"));
                    f.setEmail(rs.getString("email"));
                    f.setSenha(rs.getString("senha"));
                    f.setCpf(rs.getString("cpf"));
                    f.setSexo(rs.getString("sexo"));
                    f.setNomeSocial(rs.getString("nomeSocial"));
                    f.setNacionalidade(rs.getString("nacionalidade"));
                    f.setDtNasc(rs.getDate("dtNasc"));
                    f.setTurno(rs.getString("turno"));
                    f.setMetaMensal(rs.getDouble("metaMensal"));
                    f.setCargo(rs.getInt("fkcargo"));
                    return f;
                },
                email, senha
        );

        if (usuarios.isEmpty()) {
            return null;
        }

        return usuarios.get(0);
    }

    public Funcionario retornoUsuario(String email){

        String select = "select nome from funcionario where email = ?";

        List<Funcionario> lista = jdbcTemplate.query(
                select,
                new BeanPropertyRowMapper<>(Funcionario.class),
                email
        );
        if (lista.isEmpty()) {
            return null;
        }
        return lista.get(0);
    }


}
