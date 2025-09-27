package com.example.tiendaropa.model

import java.time.LocalDate

class UsuarioDto(
    val id: Long,
    val dni: String,
    val nombre: String,
    var direccion: String,
    val fechaNacimiento: LocalDate,
    var telefono: String,
    var email: String,
    var password: String,
    //0* pedidos
    var pedidos: Set<PedidoDto>
)