package com.example.tiendaropa.model

import com.example.tiendaropa.model.enumeraciones.Color
import com.example.tiendaropa.model.enumeraciones.Talla

class Producto(
    val id: Long,
    var nombre: String,
    var marca: String,
    var precioInicial: Double,
    var precioFinal: Double,
    var talla: Talla,
    var color: Color,
    var botones: Int,
    var bolsillos: Int,
    var conCapucha: Boolean,
    var nivelAbrigo: Int,
) {
    override fun toString(): String {
        return "Producto(id=$id, nombre='$nombre', marca='$marca', precioFinal=$precioFinal, talla=$talla)"
    }
}