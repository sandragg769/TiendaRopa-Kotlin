package com.example.tiendaropa.model

import com.example.tiendaropa.model.enumeraciones.Color
import com.example.tiendaropa.model.enumeraciones.Talla

data class ProductoDto(
    val id: Long = 0,
    var nombre: String? = null,
    var marca: String? = null,
    var precioInicial: Double = 0.0,
    var precioFinal: Double = 0.0,
    var talla: Talla? = null,
    var color: Color? = null,
    var botones: Int = 0,
    var bolsillos: Int = 0,
    var conCapucha: Boolean = false,
    var nivelAbrigo: Int = 0
)