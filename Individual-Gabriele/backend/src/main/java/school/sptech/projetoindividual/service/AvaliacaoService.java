package school.sptech.projetoindividual.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import school.sptech.projetoindividual.Entity.Avaliacao;
import school.sptech.projetoindividual.Entity.Filme;
import school.sptech.projetoindividual.Entity.Usuario;
import school.sptech.projetoindividual.controller.AtualizarAvaliacaoDTO;
import school.sptech.projetoindividual.controller.CriarAvaliacaoDTO;
import school.sptech.projetoindividual.repository.AvaliacaoRepository;
import school.sptech.projetoindividual.repository.FilmeRepository;
import school.sptech.projetoindividual.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private AvaliacaoRepository avaliacaoRepository;
    private UsuarioRepository usuarioRepository;
    private FilmeRepository filmeRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository,
                          UsuarioRepository usuarioRepository,
                          FilmeRepository filmeRepository){
        this.avaliacaoRepository = avaliacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    public Integer criarAvaliacao(CriarAvaliacaoDTO criarAvaliacaoDTO){
        Usuario usuario = usuarioRepository.findById(criarAvaliacaoDTO.idUsuario()).orElseThrow();
        Filme filme = filmeRepository.findById(criarAvaliacaoDTO.idFilme()).orElseThrow();

        var entity = new Avaliacao(null,
                criarAvaliacaoDTO.nota(),
                criarAvaliacaoDTO.favorito(),
                criarAvaliacaoDTO.descricao(),
                usuario,
                filme);
        var avaliacaoSave = avaliacaoRepository.save(entity);
        return avaliacaoSave.getId();
    }

    public Optional<Avaliacao> getAvaliacaoById(String avaliacaoId) {
        try {
            return avaliacaoRepository.findById(Integer.parseInt(avaliacaoId));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public List<Avaliacao> listarAvaliacoes(Integer idUsuario) {
        return avaliacaoRepository.findAll();
    }

   @Transactional
    public void updateAvaliacao(String avaliacaoId,
                              AtualizarAvaliacaoDTO atualizarAvaliacaoDTO) {
        var id = Integer.parseInt(avaliacaoId);
        var avaliacaoOpt = avaliacaoRepository.findById(id);
        if (avaliacaoOpt.isPresent()) {
            var avaliacao = avaliacaoOpt.get();

            if (atualizarAvaliacaoDTO.nota() != null) {
                avaliacao.setNota(atualizarAvaliacaoDTO.nota());
            }
            if (atualizarAvaliacaoDTO.favorito() != null) {
                avaliacao.setFavorito(atualizarAvaliacaoDTO.favorito());
            }
            if (atualizarAvaliacaoDTO.descricao() != null) {
                avaliacao.setDescricao(atualizarAvaliacaoDTO.descricao());
            }

            avaliacaoRepository.save(avaliacao);
        }
    }

    @Transactional
    public void deletarAvaliacao(String avaliacaoId) {
        var id = Integer.parseInt(avaliacaoId);
        var avaliacaioExists = avaliacaoRepository.existsById(id);
        if (avaliacaioExists) {
            avaliacaoRepository.deleteById(id);
        }
    }
    public List<Avaliacao> listarPorUsuario(Integer idUsuario) {
        return avaliacaoRepository.findByIdUsuarioId(idUsuario);
    }
}
