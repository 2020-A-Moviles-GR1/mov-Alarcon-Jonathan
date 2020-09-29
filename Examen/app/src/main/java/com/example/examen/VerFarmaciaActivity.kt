package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_ver_farmacia.*

class VerFarmaciaActivity : AppCompatActivity() {

    val urlPrincipal = "http://192.168.1.106:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_farmacia)

        val url = urlPrincipal + "/farmacia"
        val nombre = intent.getStringExtra("Nombre Farmacia")
        val direccion = intent.getStringExtra("Direccion Farmacia")
        val trabajadores = intent.getIntExtra("Num Trabajadores", 0)
        val compra = intent.getFloatExtra("Compra", 0f)
        val horario = intent.getBooleanExtra("Atencion", true)
        var atencion = ""
        if(horario == true){
            atencion = "Atiende las 24 horas"
        } else{
            atencion = "No atiende las 24 horas"
        }

        Log.i("recuperar","el nombre es: ${nombre}")
        Log.i("recuperar","la direccion es: ${direccion}")
        Log.i("recuperar","la direccion es: ${horario}")
        Log.i("recuperar","la direccion es: ${atencion}")

        tv_nombre.text = nombre.toString()
        tv_direccion.text = direccion.toString()
        tv_trabajadores.text = trabajadores.toString()
        tv_compra.text = compra.toString()
        tv_atencion.text = atencion.toString()

        url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Nombre: ${error}")
                    }
                    is Result.Success -> {
                        val farmaciaString = result.get()
                        Log.i("http-klaxon1", "Nombre: ${farmaciaString}")
                    }
                }
            }

        btn_volver.setOnClickListener { volver() }


    }

    fun volver(){
        val intentVolver = Intent(
            this,
            GestionFarmaciaActivity :: class.java
        )
        startActivity(intentVolver)
    }


}
