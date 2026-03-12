package school.sptech.projetoindividual.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import school.sptech.projetoindividual.model.Usuarios;

@Repository
public class UsuarioRepository {
    private final JdbcTemplate template;

    public UsuarioRepository(JdbcTemplate template) {
        this.template = template;
    }


    public void inserirTabela(Usuarios usuario){
        String scriptSql = "insert into usuario (nome, sobrenome,  dtNascimento,email, senha, clube) " +
                "values (?, ?, ?, ?, ?, ?)";

        template.update(scriptSql, usuario.getNome(), usuario.getSobrenome(), usuario.getDtNascimento(),usuario.getEmail(), usuario.getSenha(), usuario.getClubes());
    }
}
