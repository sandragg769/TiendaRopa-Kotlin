package com.example.tiendaropa

import com.example.tiendaropa.model.LineaPedidoDto
import com.example.tiendaropa.model.PedidoDto
import com.example.tiendaropa.model.ProductoDto
import com.example.tiendaropa.model.UsuarioDto
import com.example.tiendaropa.model.enumeraciones.Color
import com.example.tiendaropa.model.enumeraciones.EstadoPedido
import com.example.tiendaropa.model.enumeraciones.Talla
import java.time.LocalDate


fun main() {
    val controlador = Controlador()

    //CREAR OBJETOS
    // crear usuario
    val usuario = UsuarioDto(
        1,
        "12345678A",
        "Sandra",
        "Alguna",
        LocalDate.of(2005, 9, 12),
        "644489756",
        "sandra@gmail.com",
        "123",
        listOf()
    )

    // Crear pedidos
    val pedido1 = PedidoDto(
        id = 1,
        fecha = LocalDate.of(2025, 1, 15),
        estado = EstadoPedido.FINALIZADO,
        usuario = usuario
    )

    val pedido2 = PedidoDto(
        id = 2,
        fecha = LocalDate.of(2024, 3, 5),
        estado = EstadoPedido.PENDIENTE,
        usuario = usuario
    )

    val pedido3 = PedidoDto(
        id = 3,
        fecha = LocalDate.of(2025, 5, 10),
        estado = EstadoPedido.FINALIZADO,
        usuario = usuario
    )

    val pedido4 = PedidoDto(
        id = 4,
        fecha = LocalDate.of(2025, 7, 21),
        estado = EstadoPedido.FINALIZADO,
        usuario = usuario
    )

    val pedido5 = PedidoDto(
        id = 5,
        fecha = LocalDate.of(2024, 12, 1),
        estado = EstadoPedido.FINALIZADO,
        usuario = usuario
    )


    // Crear productos
    val producto1 =
        ProductoDto(1, "Camiseta", "Nike", 25.0, 20.0, Talla.M, Color.AZUL, 0, 0, false, 1)
    val producto2 =
        ProductoDto(2, "Pantalon", "Adidas", 40.0, 35.0, Talla.L, Color.NEGRO, 0, 2, false, 2)
    val producto3 =
        ProductoDto(3, "Chaqueta", "Nike", 70.0, 60.0, Talla.L, Color.ROJO, 5, 4, true, 5)
    val producto4 =
        ProductoDto(4, "Zapatillas", "Puma", 90.0, 80.0, Talla.S, Color.BLANCO, 0, 0, false, 0)
    val producto5 =
        ProductoDto(5, "Sudadera", "Nike", 55.0, 50.0, Talla.L, Color.BEIGE, 2, 1, true, 3)
    val producto6 =
        ProductoDto(6, "Bufanda", "Zara", 20.0, 15.0, Talla.L, Color.VERDE, 0, 0, false, 1)
    //meterlos en una lista
    val productos = listOf(producto1, producto2, producto3, producto4, producto5, producto6)

    // Crear líneas de pedido
    val linea1 = LineaPedidoDto(
        id = 1,
        cantidad = 2,
        pedidoDto = pedido1,
        producto = producto1
    )

    val linea2 = LineaPedidoDto(
        id = 2,
        cantidad = 1,
        pedidoDto = pedido1,
        producto = producto2
    )

    val linea3 = LineaPedidoDto(
        id = 3,
        cantidad = 3,
        pedidoDto = pedido2,
        producto = producto3
    )
    val linea4 = LineaPedidoDto(
        id = 4,
        cantidad = 4,
        pedidoDto = pedido3,
        producto = producto1
    )

    val linea5 = LineaPedidoDto(
        id = 5,
        cantidad = 2,
        pedidoDto = pedido3,
        producto = producto5
    )

    val linea6 = LineaPedidoDto(
        id = 6,
        cantidad = 1,
        pedidoDto = pedido4,
        producto = producto2
    )

    val linea7 = LineaPedidoDto(
        id = 7,
        cantidad = 2,
        pedidoDto = pedido4,
        producto = producto4
    )

    val linea8 = LineaPedidoDto(
        id = 8,
        cantidad = 5,
        pedidoDto = pedido5,
        producto = producto1
    )

    val linea9 = LineaPedidoDto(
        id = 9,
        cantidad = 1,
        pedidoDto = pedido5,
        producto = producto6
    )


    //UNIR OBJETOS (no se puede hacer antes ya que no hay x objetos creados)
    //meter las lineas de pedido a los pedidos
    pedido1.lineasPedido = listOf(linea1, linea2)
    pedido2.lineasPedido = listOf(linea3)
    pedido3.lineasPedido = listOf(linea4, linea5)
    pedido4.lineasPedido = listOf(linea6, linea7)
    pedido5.lineasPedido = listOf(linea8, linea9)

    //meter los pedidos al usuario
    usuario.pedidos = listOf(pedido1, pedido2, pedido3, pedido4, pedido5)


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
    val fechaInicio = LocalDate.of(2025, 1, 1)
    val fechaFin = LocalDate.now()
    //da error con el toString por defecto asiq lo escribo yo y así no falla
    controlador.pedidosEnRangoFecha(usuario, fechaInicio, fechaFin).forEach {
        //así se hace un "printf"
        println("Pedido ${it.id}, Fecha=${it.fecha}, Estado=${it.estado}")
    }

    //crear lista (ficticia) con todos los pedidos para probar el metodo
    val pedidos = listOf(pedido1, pedido2, pedido3, pedido4, pedido5)

    println("6 Productos mas vendidos:")
    controlador.productosMasVendidos(6, pedidos).forEach { println(it) }

    println("Productos mas vendidos (5 por defecto):")
    controlador.productosMasVendidos(listaPedidos = pedidos).forEach { println(it) }


}


