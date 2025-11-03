package com.pruebacastores.inventario.services;

import com.pruebacastores.inventario.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesServices extends JpaRepository<Rol, Integer> {
}
