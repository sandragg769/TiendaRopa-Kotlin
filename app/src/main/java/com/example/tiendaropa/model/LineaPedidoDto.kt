package com.example.tiendaropa.model

data class LineaPedidoDto(
    var id: Int = 0,
    var cantidad: Int = 0,
    var pedidoDto: PedidoDto,
    //0 1 producto
    var producto: ProductoDto
)
