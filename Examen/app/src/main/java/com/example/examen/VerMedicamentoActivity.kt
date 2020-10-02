package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_ver_medicamento.*

class VerMedicamentoActivity : AppCompatActivity() {

    val urlPrincipal = "http://192.168.1.105:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_medicamento)

        val url = urlPrincipal + "/medicamento"
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

        url.httpGet()
            .responseString { req, res, result ->
                when(result){
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is com.github.kittinunf.result.Result.Success -> {
                        val medicamentoString = result.get()
                        Log.i("http-klaxon1", "${medicamentoString}")
                    }
                }
            }

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
