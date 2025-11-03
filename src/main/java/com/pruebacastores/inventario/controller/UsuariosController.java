package com.pruebacastores.inventario.controller;

import com.pruebacastores.inventario.model.Usuario;
import com.pruebacastores.inventario.services.UsuariosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping({"","/"})
    public String getUsuarios(Model model){
        List<Usuario>  usuarios = usuariosService.findAll();
        model.addAttribute("usuarios",usuarios);
        return "usuarios/index";
    }

    @GetMapping("/login")
    public String mostrarLogin(){
        return "/login/index.html";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String correo,
            @RequestParam String contrasena,
            HttpSession session,
            Model model
    ){
        Optional<Usuario> usuario = usuariosService.findByCorreoAndContrasena(correo,contrasena);

        if(usuario.isPresent()){
            session.setAttribute("usuario",usuario.get());
            return "redirect:/productos/";
        }
        else {
            model.addAttribute("Error","Usuario o contrasena no existentes");
            return "/login/index";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/usuarios/login";
    }
}
