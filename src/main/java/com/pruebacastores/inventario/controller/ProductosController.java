package com.pruebacastores.inventario.controller;

import com.pruebacastores.inventario.model.Historial;
import com.pruebacastores.inventario.model.Producto;
import com.pruebacastores.inventario.model.ProductoDTO;
import com.pruebacastores.inventario.model.Usuario;
import com.pruebacastores.inventario.services.HistorialesService;
import com.pruebacastores.inventario.services.ProductosService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    private ProductosService productosService;

    @Autowired
    private HistorialesService historialesService;

    @GetMapping({"","/"})
    public String mostrarInvenatrio(Model model) {
        List<Producto> productos = productosService.findAll();
        model.addAttribute("productos",productos);
        return "/inventario/index";
    }

    @GetMapping("/obtener")
    public String getProductos(Model model) {
        List<Producto> productos = productosService.findAll();
        model.addAttribute("productos",productos);
        return "/productos/index";
    }

    @GetMapping("/crear")
    public String mostrarCrearProducto(Model model) {
        ProductoDTO productoDTO = new  ProductoDTO();
        model.addAttribute("productoDTO",productoDTO);
        return "/productos/crear.html";
    }

    @PostMapping("/crear")
    public String crearProducto(
            @Valid @ModelAttribute ProductoDTO productoDTO,
            BindingResult result) {
        if (result.hasErrors())
            return "productos/crear.html";

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());

        productosService.save(producto);

        return "redirect:/productos/obtener";
    }

    @GetMapping("/agregar")
    public String mostrarAgregarExistencia(
            Model model,
            @RequestParam int id
    ){
        try{
            Producto producto = productosService.findById(id).get();
            model.addAttribute("producto",producto);

            ProductoDTO productoDTO = new  ProductoDTO();
            productoDTO.setNombre(producto.getNombre());

            model.addAttribute("productoDTO",productoDTO);

        }
        catch(Exception e){
            System.out.println("Exception "+e.getMessage());
            return "redirect:/productos/obtener";
        }
        return "productos/entrada.html";
    }

    @PostMapping("/agregar")
    public String actualizarProducto(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute ProductoDTO productoDTO,
            BindingResult result,
            HttpSession  session
    ){
        try {
            Producto producto = productosService.findById(id).get();
            model.addAttribute("producto",producto);
            if (result.hasErrors())
                return "productos/entrada.html";

            if (productoDTO.getCantidad() < producto.getCantidad()){
                result.rejectValue(
                        "cantidad",
                        "error.cantidad",
                        "La nueva cantidad no puede ser menor a la actual ("+producto.getCantidad()+")"
                );
                model.addAttribute("productoDTO",productoDTO);
                return "productos/entrada.html";
            }
            else {
                producto.setCantidad(productoDTO.getCantidad());
            }

            productosService.save(producto);

            Usuario usuario = (Usuario) session.getAttribute("usuario");

            Historial historial = new Historial();
            historial.setTipo("Entrada");
            historial.setProducto(producto);
            historial.setUsuario(usuario);
            historial.setFechaHora(LocalDateTime.now());
            historialesService.save(historial);
        }
        catch (Exception e){
            System.out.println("Exception "+e.getMessage());
        }
        return "redirect:/productos/obtener";
    }

    @GetMapping("/baja")
    public String darBaja(@RequestParam int id){
        try {
            Producto producto = productosService.findById(id).get();

            producto.setEstatus("Baja");
            productosService.save(producto);

            return "redirect:/productos/obtener";
        }
        catch (Exception e){
            System.out.println("Exception "+e.getMessage());
            return "redirect:/productos/obtener";
        }
    }

    @GetMapping("/alta")
    public String darAlta(@RequestParam int id){
        try {
            Producto producto = productosService.findById(id).get();

            producto.setEstatus("Activo");
            productosService.save(producto);

            return "redirect:/productos/obtener";
        }
        catch (Exception e){
            System.out.println("Exception "+e.getMessage());
            return "redirect:/productos/obtener";
        }
    }

    @GetMapping("/restar")
    public String mostrarRestarExistencia(
            Model model,
            @RequestParam int id
    ){
        try{
            Producto producto = productosService.findById(id).get();
            model.addAttribute("producto",producto);

            ProductoDTO productoDTO = new  ProductoDTO();
            productoDTO.setNombre(producto.getNombre());

            model.addAttribute("productoDTO",productoDTO);

        }
        catch(Exception e){
            System.out.println("Exception "+e.getMessage());
            return "redirect:/productos/";
        }
        return "productos/salida.html";
    }

    @PostMapping("/restar")
    public String restarProducto(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute ProductoDTO productoDTO,
            BindingResult result,
            HttpSession session
    ){
        try {
            Producto producto = productosService.findById(id).get();
            model.addAttribute("producto",producto);
            if (result.hasErrors())
                return "productos/salida.html";

            if (productoDTO.getCantidad() > producto.getCantidad()){
                result.rejectValue(
                        "cantidad",
                        "error.cantidad",
                        "La nueva cantidad no puede ser mayor a la actual ("+producto.getCantidad()+")"
                );
                model.addAttribute("productoDTO",productoDTO);
                return "productos/salida.html";
            } else if (productoDTO.getCantidad() < 0) {
                result.rejectValue(
                        "cantidad",
                        "error.cantidad",
                        "La nueva cantidad no puede ser mayor a la actual ("+producto.getCantidad()+")"
                );
                model.addAttribute("productoDTO",productoDTO);
                return "productos/salida.html";
            } else {
                producto.setCantidad(productoDTO.getCantidad());
            }

            productosService.save(producto);

            Usuario usuario = (Usuario) session.getAttribute("usuario");

            Historial historial = new Historial();
            historial.setTipo("Salida");
            historial.setProducto(producto);
            historial.setUsuario(usuario);
            historial.setFechaHora(LocalDateTime.now());
            historialesService.save(historial);
        }
        catch (Exception e){
            System.out.println("Exception "+e.getMessage());
        }
        return "redirect:/productos/";
    }
}
