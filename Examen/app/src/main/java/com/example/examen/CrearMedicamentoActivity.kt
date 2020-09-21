package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_crear_medicamento.*

class CrearMedicamentoActivity : AppCompatActivity() {

    val urlPrincipal = "http://192.168.1.62:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_medicamento)

        var listaMed = arrayListOf<MedicamentoAtributos>()

        listaMed = ServicioBDDMemoria.listaMedicamentos

        val nombreFarma = intent.getStringExtra("Nombre Far")
        btn_crearMed.setOnClickListener { obtenerMed(nombreFarma) }
        bt_atras.setOnClickListener { irPantallaAtras() }
    }

    fun obtenerMed(nombreFarm : String){
        val url = urlPrincipal + "/medicamento"
        val uso : Boolean
        val nombreMed = tv_nombreMed.text.toString()
        val precioMed = tv_precioMed.text.toString().toFloat()
        val fechaMed = tv_fechaMed.text.toString()
        val unidadesMed = tv_unidadesMed.text.toString().toInt()

        if(rb_Si.isSelected){
            uso = true
        } else{
            uso = false
        }

        ServicioBDDMemoria.agregarMedicamento(2,nombreFarm,nombreMed,precioMed,fechaMed,unidadesMed,uso)
        val parametrosMedicamento = listOf(
            "nombreFarmacia" to nombreFarm,
            "nombreMedicamento" to nombreMed,
            "precioMedicamento" to precioMed,
            "fechaMedicamento" to fechaMed,
            "unidadesMedicamento" to unidadesMed,
            "prevencion" to uso
        )

        url.httpPost(parametrosMedicamento)
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

    }

    fun irPantallaAtras(){
        val intentAtras = Intent(
            this,
            GestionFarmaciaActivity :: class.java
        )
        startActivity(intentAtras)
    }

}
