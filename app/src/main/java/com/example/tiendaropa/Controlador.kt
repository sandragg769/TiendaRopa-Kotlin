package com.example.tiendaropa

import com.example.tiendaropa.model.ProductoDto
import com.example.tiendaropa.model.UsuarioDto
import com.example.tiendaropa.model.enumeraciones.Talla

class Controlador {
    //filtrar productos por precio en un rango
    fun filtrarProductoPorPrecio(
        listaProductos: List<ProductoDto>,
        minimo: Double,
        maximo: Double
    ): List<ProductoDto> {
        return listaProductos.filter { it.precioFinal in minimo..maximo }
    }

    //filtrar productos por talla (por defecto talla L
    fun filtrarProductoPorTalla(
        listaProductos: List<ProductoDto>,
        talla: Talla = Talla.L
    ): List<ProductoDto> {
        return listaProductos.filter { it.talla == talla }
    }

    //ordenar lista de prodcutos por precio, se pasa por parametro el orden (ascendente o descendente que será un bool)
    fun ordenarProductoPorPrecio(
        listaProductos: List<ProductoDto>,
        acdencendente: Boolean
    ): List<ProductoDto> {
        if (acdencendente) return listaProductos.sortedBy { it.precioFinal }
        else return listaProductos.sortedByDescending { it.precioFinal }
    }

    //contar productos que sean de una marca en concreto
    fun contarPorMarca(
        listaProductos: List<ProductoDto>,
        marca: String
    ): Int {
        return listaProductos.filter { it.marca == marca }.count()
    }

    //Calcula el gasto total del usuario, teniendo en cuenta todos los pedidos del usuario.
    // Si se le pasa 1 año (p.ej 2025) calcula el gasto total de ese año, en caso contrario,
    // calcula el gasto de todos los años.
    //? al poder ser null
    fun gatoTotal(
        usuario: UsuarioDto,
        anio: Int?
    ): Double {

    }


}