package com.example.android

class Entrenador(
    var nombre: String,
    var apellido: String
) {

    override fun toString(): String {
        return "${this.nombre} ${this.apellido}"
    }

}