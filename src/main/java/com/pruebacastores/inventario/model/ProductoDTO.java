package com.pruebacastores.inventario.model;

import jakarta.validation.constraints.*;

public class ProductoDTO {
    @NotEmpty(message = "El nombre es requerido")
    private String nombre;

    private int cantidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
