package com.example.tiendaropa

import com.example.tiendaropa.model.PedidoDto
import com.example.tiendaropa.model.ProductoDto
import com.example.tiendaropa.model.UsuarioDto
import com.example.tiendaropa.model.enumeraciones.Talla
import java.time.LocalDate
import kotlin.collections.filter

class Controlador {
    //filtrar productos por precio en un rango
    fun filtrarProductoPorPrecio(
        listaProductos: List<ProductoDto>, minimo: Double, maximo: Double
    ): List<ProductoDto> {
        return listaProductos.filter { it.precioFinal in minimo..maximo }
    }

    //filtrar productos por talla (por defecto talla L)
    fun filtrarProductoPorTalla(
        listaProductos: List<ProductoDto>, talla: Talla = Talla.L
    ): List<ProductoDto> {
        return listaProductos.filter { it.talla == talla }
    }

    //ordenar lista de productos por precio, se pasa por parámetro el orden (ascendente o descendente que será un boolean)
    fun ordenarProductoPorPrecio(
        listaProductos: List<ProductoDto>, ascencendente: Boolean
    ): List<ProductoDto> {
        return if (ascencendente) listaProductos.sortedBy { it.precioFinal }
        else listaProductos.sortedByDescending { it.precioFinal }
    }

    //contar productos que sean de una marca en concreto
    fun contarPorMarca(
        listaProductos: List<ProductoDto>, marca: String
    ): Int {
        return listaProductos.count { it.marca == marca }
    }

    // calcula el gasto total del usuario, teniendo en cuenta todos los pedidos del usuario.
    // Si se le pasa 1 año (p.ej 2025) calcula el gasto total de ese año, en caso contrario,
    // calcula el gasto de todos los años.
    fun gastoTotal(
        usuario: UsuarioDto,
        //puede ser null
        anio: Int?
    ): Double {
        //ver si se ha pasado año por parámetro o no
        val listaPedidos = if (anio == null) {
            //si no se le ha pasado se obtiene sin flitar por año
            usuario.pedidos
        } else {
            //si sí se le ha pedido se filtra comparando el año
            usuario.pedidos.filter { pedido ->
                pedido.fecha.year == anio
            }
        }
        //con este metodo se saca el total de la suma del precio de los pedidos
        return sumarPrecioPedidos(listaPedidos)
    }

    //METODO AUXILIAR para sumar el precio de todos los pedidos de la lista
    fun sumarPrecioPedidos(listaPedidos: List<PedidoDto>): Double {
        //será la suma total a devolver
        var acum = 0.0

        //se le pasa una lista de pedidos y esta se recorre
        for (pedido in listaPedidos) {
            //en un pedido concreto mirar las lineas de pedido
            for (lineaPedido in pedido.lineasPedido!!) {
                //sumar el precio final del producto por la cantidad de productos, para obtener la suma de los pedidos de la lista
                acum += lineaPedido.producto.precioFinal * lineaPedido.cantidad
            }
        }
        return acum
    }

    // dado un usuario, una fecha inicio y una fecha fin, devuelva los pedidos en ese rango de fechas.
    fun pedidosEnRangoFecha(
        usuario: UsuarioDto, fechaInicio: LocalDate, fechaFin: LocalDate
    ): List<PedidoDto> {
        //sacar el rango entre las fechas
        val rango = fechaInicio..fechaFin
        //devolver la lista de pedidos de un usuario dentro del rango de fechas filtrando
        return usuario.pedidos.filter { it.fecha in rango }
    }

    // dados todos los pedidos del sistema, obtiene un ranking de los 5 productos más vendidos (5 productos
    // por defecto, pero podría pasarse otro número como parámetro).
    fun productosMasVendidos(
        //5 por defecto
        cantidad: Int = 5,
        listaPedidos: List<PedidoDto>
    ): List<Pair<ProductoDto, Int>> {
        //mapa a rellenar
        val conteoProductos: MutableMap<ProductoDto, Int> = mutableMapOf()

        //contar cantidad de cada producto de cada pedido e ir guardandolo en un mapa de Producto-cantidad
        //recorrer los pedidos y dentro de cada pedido las lineas de pedido
        listaPedidos.forEach { pedido ->
            pedido.lineasPedido?.forEach { linea ->
                //obtenemos el producto (el cual será la clave del mapa)
                val producto = linea.producto
                //guardamos en el mapa (como valor de la clave producto) la cantidad
                //con el getOrDefault, buscamos el valor del producto en el mapa, si no existe le ponemos 0 y si si está devuelve la cantidad acumulada
                conteoProductos[producto] =
                    conteoProductos.getOrDefault(producto, 0) + linea.cantidad
            }
        }

        //una vez tenemos el mapa relleno, ordenarlo por la cantidad (es decir el valor) y devolver una lista de tantos productos como cantidad (del parámetro) se haya indicado
        //pasamos el mapa a lista (una lista de Pair)
        //ordenar esta lista (descendentemente para ver los más vendidos arriba) (second es el valor) y devolver cantidad (con take)
        return conteoProductos.toList().sortedByDescending { it.second }.take(cantidad)
    }


}