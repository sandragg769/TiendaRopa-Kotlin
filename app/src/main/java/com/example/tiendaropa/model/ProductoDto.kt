package com.example.tiendaropa.model

import com.example.tiendaropa.model.enumeraciones.Color
import com.example.tiendaropa.model.enumeraciones.Talla

class ProductoDto(
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
    //1 linea de pedido
    var lineaPedido: LineaPedidoDto
)