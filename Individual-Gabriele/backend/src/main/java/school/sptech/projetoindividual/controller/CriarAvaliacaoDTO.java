package school.sptech.projetoindividual.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CriarAvaliacaoDTO(
    Double nota,
    Boolean favorito,
    String descricao,
    @JsonProperty("fk_usuario")
    Integer idUsuario,
    @JsonProperty("fk_filme")
    Integer idFilme) {
}
