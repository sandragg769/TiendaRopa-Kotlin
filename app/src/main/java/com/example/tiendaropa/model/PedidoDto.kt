package com.example.tiendaropa.model

import com.example.tiendaropa.model.enumeraciones.EstadoPedido
import java.time.LocalDate

data class PedidoDto(
    val id: Int = 0,
    var fecha: LocalDate,
    var estado: EstadoPedido = EstadoPedido.PENDIENTE,
    var usuario: UsuarioDto,
    //1* lineas de pedido
    var lineasPedido: List<LineaPedidoDto>? = null
)