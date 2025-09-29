package com.example.tiendaropa.model

import java.time.LocalDate
import java.util.Date

data class UsuarioDto(
    val id: Long,
    val dni: String,
    val nombre: String,
    var direccion: String,
    val fechaNacimiento: Date,
    var telefono: String,
    var email: String,
    var password: String,
    //0* pedidos
    var pedidos: List<PedidoDto>
)