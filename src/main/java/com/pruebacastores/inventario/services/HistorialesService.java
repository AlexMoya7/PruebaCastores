package com.pruebacastores.inventario.services;

import com.pruebacastores.inventario.model.Historial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialesService extends JpaRepository<Historial, Integer> {
    List<Historial> findByTipo(String tipo);
}
