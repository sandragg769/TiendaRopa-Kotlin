package com.example.tiendaropa.model

import java.time.LocalDate

data class UsuarioDto(
    val id: Long = 0,
    val dni: String? = null,
    val nombre: String? = null,
    var direccion: String? = null,
    val fechaNacimiento: LocalDate,
    var telefono: String? = null,
    var email: String? = null,
    var password: String? = null,
    //0* pedidos
    var pedidos: List<PedidoDto>
)