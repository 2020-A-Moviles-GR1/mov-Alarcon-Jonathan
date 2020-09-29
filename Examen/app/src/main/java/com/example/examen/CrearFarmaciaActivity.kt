package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_crear_farmacia.*

class CrearFarmaciaActivity : AppCompatActivity() {


    //private EditText nombre
    //private EditText nombre;
    //lateinit var nombre : EditText
    //lateinit var botonCrear : Button
    //lateinit var vista : TextView
    val urlPrincipal = "http://192.168.1.106:1337"
    var contador = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_farmacia)

        var listaFarmacias = arrayListOf<FarmaciaAtributos>()



        //nombre = findViewById(R.id.txt_nombre)
        //vista = findViewById(R.id.texto)
        //botonCrear = findViewById(R.id.btn_datosCrear)
        //botonCrear.setOnClickListener { this }
        listaFarmacias = ServicioBDDMemoria.listaFarmacias

        Log.i("Datos en Crear","La lista completa: ${listaFarmacias}")

        btn_datosCrear.setOnClickListener { obtenerDatos() }
        btn_cancelar.setOnClickListener { irAtras() }

        //val nombreDato = txt_nombre.text.toString()
        //val direccionDato = txt_direccion.text.toString()
        //val trabajadoresDatos = txt_trabajadores.text.toString()
        //val compraDato = txt_compra.text.toString()
        //val horarioDato = txt_atencion.text.toString()

        //Log.i("datito","nombre ${nombreDato}")

    }

    fun obtenerDatos(){
        val url = urlPrincipal + "/farmacia"
        contador = contador + 1
        var atencion = true
        val nombreDato = txt_nombre.text.toString()
        val direccionDato = txt_direccion.text.toString()
        val trabajadoresDatos: Int = txt_trabajadores.text.toString().toInt()
        val compraDato = txt_compra.text.toString().toFloat()
        val horarioDato = txt_atencion.text.toString().toUpperCase()
        if (horarioDato == "NO"){
            atencion = false
        }
        ServicioBDDMemoria.agregar_farmacia(1,nombreDato,direccionDato,trabajadoresDatos,compraDato, atencion)
        Log.i("datito","nombre ${nombreDato}")
        Log.i("datito","nombre ${direccionDato}")
        Log.i("datito","nombre ${trabajadoresDatos}")
        Log.i("datito","nombre ${compraDato}")
        Log.i("datito","nombre ${horarioDato}")
        Log.i("datito","nombre ${atencion}")

        val parametrosFarmacia = listOf(
            "nombreFarmacia" to nombreDato,
            "direccionFarmacia" to direccionDato,
            "numeroTrabajadores" to trabajadoresDatos,
            "compra" to compraDato,
            "atencion" to atencion
        )

        url.httpPost(parametrosFarmacia)
            .responseString { req, res, result ->
                when(result){
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is com.github.kittinunf.result.Result.Success -> {
                        val farmaciaString = result.get()
                        Log.i("http-klaxon1", "${farmaciaString}")
                    }
                }
            }

        /*val intentExplicito = Intent(
            this,
            MapsActivity::class.java
        )
        intentExplicito.putExtra("Nombre",nombreDato)*/

    }

    fun irAtras(){
        val intentGestionar = Intent(
            this,
            Farmacia_act :: class.java
        )
        startActivity(intentGestionar)
    }



    /*override fun onClick(view: View?) {
        var nombreDato = nombre.text
        //Log.i("Datos", "nombre $nombreDato")
        //println(nombreDato)
        vista.text = "Bienvenido $nombreDato"
    }*/

    /*fun datos(){
        val nombreDato = nombre.getText().toString()
        Log.i("Datos", "nombre $nombreDato")
    }*/
}
