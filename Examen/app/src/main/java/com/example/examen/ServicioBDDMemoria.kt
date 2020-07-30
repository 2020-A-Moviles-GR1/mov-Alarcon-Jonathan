package com.example.examen

import android.util.Log

class ServicioBDDMemoria {

    companion object{
        //val listaEntrenadores = arrayListOf<Entrenador>()
        var listaFarmacias = arrayListOf<FarmaciaAtributos>()

        fun agregar_farmacia(
            id : Int,
            nombre : String,
            direccion : String,
            trabajadores : Int,
            compra : Float,
            horario : Boolean
        ){
            //this.listaFarmacias.add(FarmaciaAtributos(2,"Sana Sana","America",25,1.5f,true))

            this.listaFarmacias.add(FarmaciaAtributos(id,nombre,direccion,trabajadores,compra,horario))
            Log.i("Lista", "La lista es: ${listaFarmacias[0]}")
            //Log.i("Lista", "La segunda lista es: ${listaFarmacias[1]}")
            Log.i("Lista", "La lista es: ${listaFarmacias}")
        }
    }

}