package com.pruebacastores.inventario.controller;

import com.pruebacastores.inventario.model.Historial;
import com.pruebacastores.inventario.services.HistorialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/historiales")
public class HistorialesController {
    @Autowired
    private HistorialesService historialesService;

    @GetMapping({"", "/"})
    public String verHistorial(@RequestParam(required = false) String tipo, Model model) {
        List<Historial> historialList;

        if (tipo != null && !tipo.isEmpty()) {
            historialList = historialesService.findByTipo(tipo);
        } else {
            historialList = historialesService.findAll();
        }

        model.addAttribute("historiales", historialList);
        return "historiales/index";
    }
}
