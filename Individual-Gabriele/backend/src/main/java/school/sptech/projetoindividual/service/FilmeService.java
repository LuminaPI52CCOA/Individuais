package school.sptech.projetoindividual.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import school.sptech.projetoindividual.Entity.Filme;
import school.sptech.projetoindividual.controller.CriarFilmeDTO;
import school.sptech.projetoindividual.repository.FilmeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {
    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    public Integer criarFilme(CriarFilmeDTO criarFilmeDTO) {
        var entity = new Filme(null,
                criarFilmeDTO.titulo(),
                criarFilmeDTO.genero(),
                criarFilmeDTO.classInd());
        var filmeSave = filmeRepository.save(entity);
        return filmeSave.getId();
    }

    public Optional<Filme> getFilmeById(String filmeId) {
        return filmeRepository.findById(Integer.parseInt(filmeId));
    }

    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }

    public void deletarFilme(Integer filmeId) {
        var id = Integer.parseInt(String.valueOf(filmeId));
        var filmeExists = filmeRepository.existsById(id);
        if(filmeExists){
            filmeRepository.deleteById(id);
        }
    }
}
