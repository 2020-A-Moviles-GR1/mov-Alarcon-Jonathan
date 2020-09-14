package com.example.android

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import java.util.*
import kotlin.collections.ArrayList

class UsuarioHttp(
    var id: Int,
    var createdAt: Long,
    var updatedAt: Long,
    var cedula: String,
    var nombre: String,
    var correo: String,
    var estadoCivil: String,
    var password: String,
    var pokemons: ArrayList<PokemonHttp>?=null
) {
    var fechaCreacion : Date
    var fechaActualizacion : Date

    init{
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }

    override fun toString(): String {
        return "Usuario:${id},${createdAt},${updatedAt},${cedula},${nombre},${correo},${estadoCivil}" +
                "${password}"
    }

}

