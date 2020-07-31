package com.example.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_blist_view.*

class BListViewActivity : AppCompatActivity() {

    val listaEntrenadores = arrayListOf<Entrenador>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        listaEntrenadores.add(Entrenador("Jonathan","Alarcon"))
        listaEntrenadores.add(Entrenador("Dayana","Rodriguez"))
        listaEntrenadores.add(Entrenador("Guillermo","Vargas"))
        listaEntrenadores.add(Entrenador("Raul","Alarcon"))
        listaEntrenadores.add(Entrenador("Jacqueline","Paillacho"))
        Log.i("Lista","Esta es la lista: ${listaEntrenadores}")

        //adaptadores
        val adaptador: ArrayAdapter<Entrenador> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, //Nombre Layout
            listaEntrenadores //Lista
        )

        lv_numeros.adapter = adaptador


        lv_numeros
            .onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.i("list-view","Posicion $position")
            }

        btn_agregar.setOnClickListener {
            agregarNombre(adaptador)
        }

    }

    fun agregarNombre(adaptador: ArrayAdapter<Entrenador>){
        listaEntrenadores.add(Entrenador("Valeria","Rodriguez"))
        listaEntrenadores.add(Entrenador("Juan","Lopez"))
        adaptador.notifyDataSetChanged()

    }



}
