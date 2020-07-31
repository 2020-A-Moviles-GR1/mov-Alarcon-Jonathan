package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_farmacia_act.*

class Farmacia_act : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmacia_act)
        btn_crear.setOnClickListener { irCrear() }
        btn_buscar.setOnClickListener { irBuscar() }
    }



    fun irCrear(){
        val intentExplicito = Intent(
            this,
            CrearFarmaciaActivity :: class.java
        )
        startActivity(intentExplicito)
    }

    fun irBuscar(){
        val intentExplicito = Intent(
            this,
            GestionFarmaciaActivity :: class.java
        )
        startActivity(intentExplicito)
    }

}
