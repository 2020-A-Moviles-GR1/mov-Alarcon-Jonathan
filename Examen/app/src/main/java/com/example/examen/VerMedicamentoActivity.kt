package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ver_medicamento.*

class VerMedicamentoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_medicamento)

        val nombreFar = intent.getStringExtra("Nombre Far")
        val nombreMedi = intent.getStringExtra("Nombre Med")
        val precioMed = intent.getFloatExtra("Precio", 0f)
        val fechaMed = intent.getStringExtra("Fecha")
        val unidadMed = intent.getIntExtra("Unidades", 0)
        val apto = intent.getBooleanExtra("Apto", true)
        val validarApto : String

        if(apto == true){
            validarApto = "El medicamento es apto para niños"
        } else{
            validarApto = "El medicamento NO es apto para niños"
        }

        t_nombreFar.text = nombreFar.toString()
        t_nomMed.text = nombreMedi.toString()
        t_precio.text = precioMed.toString()
        t_fecha.text = fechaMed.toString()
        t_unidades.text = unidadMed.toString()
        t_apto.text = validarApto

        btn_atras.setOnClickListener { irAtras() }

    }

    fun irAtras(){
        val intentAtras = Intent(
            this,
            GestionarMedicamentoActivity :: class.java
        )
        startActivity(intentAtras)
    }
}
