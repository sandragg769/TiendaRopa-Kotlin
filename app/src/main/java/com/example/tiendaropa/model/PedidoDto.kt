package com.example.tiendaropa.model

import com.example.tiendaropa.model.enumeraciones.EstadoPedido
import java.util.Date

class PedidoDto(
    val id: Int,
    var fecha: Date,
    var estado: EstadoPedido,
    var usuario: UsuarioDto,
    //1* lineas de pedido
    var lineasPedido: Set<LineaPedidoDto>
)