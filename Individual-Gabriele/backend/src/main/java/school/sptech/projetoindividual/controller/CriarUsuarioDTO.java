package school.sptech.projetoindividual.controller;

import java.time.LocalDate;

public record CriarUsuarioDTO(String nome, String email, LocalDate dataNasc, String senha) {
}
