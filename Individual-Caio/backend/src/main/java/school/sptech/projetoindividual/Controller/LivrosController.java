package school.sptech.projetoindividual.Controller;

import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import school.sptech.projetoindividual.Model.Livros;
import school.sptech.projetoindividual.Repository.LivrosRepository;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/livros")

public class LivrosController {

    private final LivrosRepository repositorio;

    public LivrosController(LivrosRepository repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping("/buscarLivros/{tituloAPI}/{genero}")
    public ResponseEntity<List<Livros>> buscarLivros(
            @PathVariable String tituloAPI,
            @PathVariable int genero) {


        try {
            String query = tituloAPI;
            String queryCodificada = URLEncoder.encode(query, StandardCharsets.UTF_8);

            String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:'"
                    + queryCodificada+ "'&printType=books&maxResults=1&key=AIzaSyCRaM6kleAvr8I9-fc9MiayI-CI_TsrOuI";


            RestTemplate restTemplate = new RestTemplate();

            Map resposta = restTemplate.getForObject(url, Map.class);

            List<Map> items = (List<Map>) resposta.get("items");

            List<Livros> livros = new ArrayList<>();

            for (Map item : items) {

                Map volumeInfo = (Map) item.get("volumeInfo");
                if (volumeInfo == null) continue;

                String titulo = (String) volumeInfo.get("title");

                List<String> autores = (List<String>) volumeInfo.get("authors");

                String autor = (autores != null && !autores.isEmpty())
                        ? autores.get(0)
                        : "Autor desconhecido";

                Livros livro = new Livros(titulo, autor, genero);

                livros.add(livro);
            }
            for (Livros l : livros){
                repositorio.cadastrar(l.getTitulo(), l.getAutor(), l.getFkCategoria());
            }

            return ResponseEntity.status(201).body(livros);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Livros>> selectLivros(){

        List<Livros> livros = repositorio.listarLivros();

        return ResponseEntity.status(200).body(livros);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivros(@PathVariable Integer id){

        Integer quantidade = repositorio.existe(id);

        if (quantidade > 0){
            repositorio.deletarLivro( id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }



}
