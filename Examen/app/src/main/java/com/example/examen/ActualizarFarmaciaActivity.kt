package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_actualizar_farmacia.*

class ActualizarFarmaciaActivity : AppCompatActivity() {

    var listaFarmacias1 = arrayListOf<FarmaciaAtributos>()
    val urlPrincipal = "http://192.168.1.106:1337"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_farmacia)

        listaFarmacias1 = ServicioBDDMemoria.listaFarmacias

        val indice = intent.getIntExtra("Indice",0)
        val nombreOld = intent.getStringExtra("Nombre Farmacia")
        val direccionOld = intent.getStringExtra("Direccion Farmacia")
        val trabajadoresOld = intent.getIntExtra("Num Trabajadores", 0)
        val compraOld = intent.getFloatExtra("Compra", 0f)
        val horario = intent.getBooleanExtra("Atencion", true)
        var atencionOld = ""
        if(horario == true){
            atencionOld = "Atiende las 24 horas"
        } else{
            atencionOld = "No atiende las 24 horas"
        }

        ed_nombre.hint = nombreOld
        ed_direccion.hint = direccionOld
        ed_trabajadores.hint = trabajadoresOld.toString()
        ed_compra.hint = compraOld.toString()
        ed_atencion.hint = atencionOld

        btn_aceptar.setOnClickListener { obtenerDatosNuevos(indice) }

        //val nombreActua = ed_nombre.text.toString()
        //val direccionActua = ed_direccion.text.toString()


        //Log.i("actualizacion","El nombre es: ${nombreActua}")
        //Log.i("actualizacion","La direccion es: ${direccionActua}")

    }

    fun obtenerDatosNuevos(posicionActua:Int){
        val posNueva = posicionActua + 8
        val url = urlPrincipal + "/farmacia" + "/" + posNueva
        Log.i("url", url)
        var nombreActua = ""
        var direccionActua = ""
        var trabajadoresActua : Int
        var compraActua = 0.0f
        var atencionActua = ""
        var atencionActua1 = true

        if (ed_nombre.length() == 0){
            //nombreActua = "Farmacia"
            nombreActua = ed_nombre.hint.toString()
            println("LLEGA AQUÍ")
        } else{
            nombreActua = ed_nombre.text.toString()
            println("LLEGA AQUÍ")
        }

        if(ed_direccion.length() == 0){
            direccionActua = ed_direccion.hint.toString()
        } else{
            direccionActua = ed_direccion.text.toString()
        }

        if(ed_trabajadores.length() == 0){
            trabajadoresActua = ed_trabajadores.hint.toString().toInt()
        } else{
            trabajadoresActua = ed_trabajadores.text.toString().toInt()
            println("LLEGA AQUÍ -> 2")
        }

        if(ed_compra.length() == 0){
            compraActua = ed_compra.hint.toString().toFloat()
        } else {
            compraActua = ed_compra.text.toString().toFloat()
        }

        if(ed_atencion.length() == 0){
            atencionActua = ed_atencion.hint.toString()
        } else {
            atencionActua = ed_atencion.text.toString()
            println("LLEGA AQUÍ -> 3")
        }

        if(atencionActua.equals("No Atiende las 24 horas")){
            atencionActua1 = false
        }


        Log.i("actualizacion","El nombre es: ${nombreActua}")
        Log.i("actualizacion","La direccion es: ${direccionActua}")
        Log.i("actualizacion","La direccion es: ${trabajadoresActua}")
        Log.i("actualizacion","La direccion es: ${compraActua}")
        Log.i("actualizacion","La direccion es: ${atencionActua1}")

        listaFarmacias1[posicionActua].nombreFarmacia = nombreActua
        listaFarmacias1[posicionActua].direccionFarmacia = direccionActua
        listaFarmacias1[posicionActua].numeroTrabajadores = trabajadoresActua
        listaFarmacias1[posicionActua].compra = compraActua
        listaFarmacias1[posicionActua].atencion = atencionActua1

        /*val parametrosFarmacia = listOf(
            "nombreFarmacia" to nombreActua,
            "direccionFarmacia" to direccionActua,
            "numeroTrabajadores" to trabajadoresActua,
            "compra" to compraActua,
            "atencion" to atencionActua1
        )*/

        val parametrosFarmacia = listOf(

            "numeroTrabajadores" to trabajadoresActua

        )

        url.httpPut(parametrosFarmacia)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Nombre: ${error}")
                    }
                    is Result.Success -> {
                        val farmaciaString = result.get()
                        Log.i("http-klaxon2", "Nombre: ${farmaciaString}")
                    }
                }
            }

        Log.i("actualizacion","La direccion es: ${listaFarmacias1}")
        val intentActua = Intent(
            this,
            GestionFarmaciaActivity :: class.java
        )
        startActivity(intentActua)
    }
}
