package com.pruebacastores.inventario.services;

import com.pruebacastores.inventario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosService extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena);
}
