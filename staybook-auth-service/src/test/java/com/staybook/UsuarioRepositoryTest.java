package com.staybook;

import com.staybook.entity.Usuario;
import com.staybook.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository repository;

    @Test
    void debeInsertarListarActualizarYEliminarUsuario() {
        Usuario usuario = Usuario.builder()
                .username("admin")
                .password("$2a$10$claveCifradaEjemplo")
                .role("ROLE_ADMIN")
                .build();

        Usuario guardado = repository.save(usuario);
        assertThat(guardado.getId()).isNotNull();

        List<Usuario> usuarios = repository.findAll();
        assertThat(usuarios).hasSize(1);

        guardado.setRole("ROLE_USER");
        Usuario actualizado = repository.save(guardado);
        assertThat(actualizado.getRole()).isEqualTo("ROLE_USER");

        repository.delete(actualizado);
        assertThat(repository.findAll()).isEmpty();
    }
}
