package com.pruebacastores.inventario.controller;

import com.pruebacastores.inventario.model.Rol;
import com.pruebacastores.inventario.services.RolesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesServices rolesServices;

    @GetMapping({"","/"})
    public String getRoles(Model model) {
        List<Rol> roles = rolesServices.findAll();
        model.addAttribute("roles", roles);
        return "roles/index";
    }
}
