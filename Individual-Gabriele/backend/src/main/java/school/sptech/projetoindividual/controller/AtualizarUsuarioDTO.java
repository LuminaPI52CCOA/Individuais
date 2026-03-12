package school.sptech.projetoindividual.controller;

import java.time.LocalDate;

public record AtualizarUsuarioDTO(String nome, String email, LocalDate dataNasc, String senha) {
}
