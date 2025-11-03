package com.pruebacastores.inventario.services;

import com.pruebacastores.inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosService extends JpaRepository<Producto, Integer> {
}
