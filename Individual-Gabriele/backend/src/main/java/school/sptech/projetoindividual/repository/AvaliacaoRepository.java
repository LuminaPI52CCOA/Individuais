package school.sptech.projetoindividual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoindividual.Entity.Avaliacao;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
    List<Avaliacao> findByIdUsuarioId(Integer idUsuario);
}
