package school.sptech.projetoindividual.service;

import org.springframework.stereotype.Service;
import school.sptech.projetoindividual.model.Usuarios;
import school.sptech.projetoindividual.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvar(Usuarios usuario){
        repository.inserirTabela(usuario);
    }
}
