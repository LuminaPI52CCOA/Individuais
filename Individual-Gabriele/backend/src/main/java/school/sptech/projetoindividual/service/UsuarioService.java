package school.sptech.projetoindividual.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.projetoindividual.Entity.Usuario;
import school.sptech.projetoindividual.controller.AtualizarUsuarioDTO;
import school.sptech.projetoindividual.controller.CriarUsuarioDTO;
import school.sptech.projetoindividual.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Integer criarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
        var entity = new Usuario(null,
                criarUsuarioDTO.nome(),
                criarUsuarioDTO.email(),
                criarUsuarioDTO.dataNasc(),
                criarUsuarioDTO.senha());
        var UsuarioSave = usuarioRepository.save(entity);
        return UsuarioSave.getId();
    }

    public Optional<Usuario> getUsuarioById(String usuarioId) {
        return usuarioRepository.findById(Integer.parseInt(usuarioId));
    }

    public List<Usuario> listUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public void updateUsuario(String usuarioId,
                              AtualizarUsuarioDTO atualizarUsuarioDTO) {
        var id = Integer.parseInt(usuarioId);
        var usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            var usuario = usuarioOpt.get();

            if (atualizarUsuarioDTO.nome() != null) {
                usuario.setNome(atualizarUsuarioDTO.nome());
            }
            if (atualizarUsuarioDTO.email() != null) {
                usuario.setEmail(atualizarUsuarioDTO.email());
            }
            if (atualizarUsuarioDTO.senha() != null) {
                usuario.setSenha(atualizarUsuarioDTO.senha());
            }
            if (atualizarUsuarioDTO.dataNasc() != null) {
                usuario.setDataNasc(atualizarUsuarioDTO.dataNasc());
            }

            usuarioRepository.save(usuario);
        }
    }

    public void deleteUsuario(String usuarioId) {
        var id = Integer.parseInt(usuarioId);
        var usuarioExists = usuarioRepository.existsById(id);
        if(usuarioExists){
            usuarioRepository.deleteById(id);
        }
    }

    public Optional<Usuario> autenticar(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }
}
