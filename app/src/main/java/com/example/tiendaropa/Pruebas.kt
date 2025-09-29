package com.example.tiendaropa

import com.example.tiendaropa.model.LineaPedido
import com.example.tiendaropa.model.PedidoDto
import com.example.tiendaropa.model.Producto
import com.example.tiendaropa.model.UsuarioDto
import com.example.tiendaropa.model.enumeraciones.Color
import com.example.tiendaropa.model.enumeraciones.EstadoPedido
import com.example.tiendaropa.model.enumeraciones.Talla
import java.time.LocalDate
import java.util.Date

fun main() {
    val controlador = Controlador()

    //CREAR OBJETOS
    // Crear productos
    val producto1 = Producto(1, "Camiseta", "Nike", 25.0, 20.0, Talla.M, Color.AZUL, 0, 0, false, 1)
    val producto2 =
        Producto(2, "Pantalon", "Adidas", 40.0, 35.0, Talla.L, Color.NEGRO, 0, 2, false, 2)
    val producto3 = Producto(3, "Chaqueta", "Nike", 70.0, 60.0, Talla.L, Color.ROJO, 5, 4, true, 5)
    val producto4 =
        Producto(4, "Zapatillas", "Puma", 90.0, 80.0, Talla.S, Color.BLANCO, 0, 0, false, 0)
    val producto5 = Producto(5, "Sudadera", "Nike", 55.0, 50.0, Talla.L, Color.BEIGE, 2, 1, true, 3)
    val producto6 = Producto(6, "Bufanda", "Zara", 20.0, 15.0, Talla.L, Color.VERDE, 0, 0, false, 1)
    //meterlos en una lista
    val productos = listOf(producto1, producto2, producto3, producto4, producto5, producto6)

    // Crear líneas de pedido
    val linea1 = LineaPedido(1, 2, producto1)
    val linea2 = LineaPedido(2, 1, producto2)
    val linea3 = LineaPedido(3, 1, producto3)
    val linea4 = LineaPedido(4, 3, producto4)

    // Crear usuario
    val usuario = UsuarioDto(
        1,
        "12345678A",
        "Sandra",
        "Alguna",
        Date(2005, 9, 12),
        "644489756",
        "sandra@gmail.com",
        "123",
        listOf()
    )


    // Crear pedidos (el 0 es Enero)
    val pedido1 =
        PedidoDto(1, Date(2025, 0, 11), EstadoPedido.PENDIENTE, usuario, listOf(linea1, linea2))

    val pedido2 = PedidoDto(2, Date(2005, 0, 5), EstadoPedido.FINALIZADO, usuario, listOf(linea3))

    val pedido3 = PedidoDto(
        3,
        Date(2023, 11, 25),
        EstadoPedido.ENTREGADO,
        usuario,
        lineasPedido = listOf(linea2, linea4)
    )


    //PROBAR METODOS
    println("Filtrar productos por precio (30 y 60)")
    //forEach para mostrarlos por pantalla
    controlador.filtrarProductoPorPrecio(productos, 30.0, 60.0).forEach { println(it) }

    println("Filtrar productos por talla L:")
    controlador.filtrarProductoPorTalla(productos, Talla.L).forEach { println(it) }

    println("Ordenar productos por precio ascendente:")
    controlador.ordenarProductoPorPrecio(productos, true).forEach { println(it) }

    println("Ordenar productos por precio descendente:")
    controlador.ordenarProductoPorPrecio(productos, false).forEach { println(it) }

    println("Contar productos de la marca Nike:")
    println("Cantidad: " + controlador.contarPorMarca(productos, "Nike"))

    println("Gasto total del usuario (todos los anios):")
    println("Gasto total: " + controlador.gastoTotal(usuario, null))

    println("Gasto total del usuario en 2025:")
    println("Gasto en 2025: " + controlador.gastoTotal(usuario, 2025))

    println("Pedidos entre 1 enero 2025 y 30 junio 2025:")
    val fechaInicio = Date(2025, 0, 1)
    val fechaFin = Date(2025, 5, 30)
    controlador.pedidosEnRangoFecha(usuario, fechaInicio, fechaFin).forEach { println(it) }

}


