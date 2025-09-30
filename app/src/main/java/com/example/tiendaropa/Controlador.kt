package com.example.tiendaropa

import android.os.Build
import androidx.annotation.RequiresApi
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

    //filtrar productos por talla (por defecto talla L
    fun filtrarProductoPorTalla(
        listaProductos: List<ProductoDto>, talla: Talla = Talla.L
    ): List<ProductoDto> {
        return listaProductos.filter { it.talla == talla }
    }

    //ordenar lista de prodcutos por precio, se pasa por parametro el orden (ascendente o descendente que será un bool)
    fun ordenarProductoPorPrecio(
        listaProductos: List<ProductoDto>, acdencendente: Boolean
    ): List<ProductoDto> {
        if (acdencendente) return listaProductos.sortedBy { it.precioFinal }
        else return listaProductos.sortedByDescending { it.precioFinal }
    }

    //contar productos que sean de una marca en concreto
    fun contarPorMarca(
        listaProductos: List<ProductoDto>, marca: String
    ): Int {
        return listaProductos.count { it.marca == marca }
    }

    //Calcula el gasto total del usuario, teniendo en cuenta todos los pedidos del usuario.
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
            //si si se le ha pedido se filtra
            //para usar el Date y que en las pruebas salga bien hay que poner esto
            usuario.pedidos.filter { pedido ->
                pedido.fecha.year == anio
            }
        }
        //con este metodo se saca el total de la suma del precio de los pedidos
        return sumarPrecioPedidos(listaPedidos)
    }

    //METODO AUXILIAR para sumar el precio de todos los pedidos de la lista
    //se le pasa una lista de pedidos y esta se recorre
    fun sumarPrecioPedidos(listaPedidos: List<PedidoDto>): Double {
        //será la suman total a devolver
        var acum: Double = 0.0

        for (pedido in listaPedidos) {
            //en un pedido concreto mirar las lineas de pedido
            for (lineaPedido in pedido.lineasPedido!!) {
                acum += lineaPedido.producto.precioFinal * lineaPedido.cantidad
            }
        }
        return acum
    }

    //Dado un usuario, una fecha inicio y una fecha fin, devuelva los pedidos en ese rango de fechas.
    fun pedidosEnRangoFecha(
        usuario: UsuarioDto, fechaInicio: LocalDate, fechaFin: LocalDate
    ): List<PedidoDto> {
        //sacar el rango
        val rango = fechaInicio..fechaFin
        //devolver la lista de pedidos de un usuario dentro del rango de fechas
        return usuario.pedidos.filter { it.fecha in rango }
    }

    //Dados todos los pedidos del sistema, obtiene un ranking de los 5 productos más vendidos (5 productos
    // por defecto, pero podría pasarse otro número como parámetro).
    fun productosMasVendidos(cantidad: Int = 5): List<ProductoDto> {
        //mapa a devolver
        var conteoProductos: MutableMap<ProductoDto, Int>
        //obtener todos los pedidos del sistema

        //contar cantidad de cada productos de cada pedido e ir guardandolo en un mapa de Producto-cantidad
        //ordenar el mapa de los productos por la cantidad (es decir el valor) y devolver la cantidad indicada

    }


}