package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_actualizar_medicamento.*

class ActualizarMedicamentoActivity : AppCompatActivity() {

    var listaMedica = arrayListOf<MedicamentoAtributos>()
    val urlPrincipal = "http://192.168.1.105:1337"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_medicamento)

        listaMedica = ServicioBDDMemoria.listaMedicamentos

        val posicion = intent.getIntExtra("Indice",0)
        val nomFar = intent.getStringExtra("Nombre Far")
        val nomMed = intent.getStringExtra("Nombre Med")
        val precio = intent.getFloatExtra("Precio", 0f)
        val fecha = intent.getStringExtra("Fecha")
        val unidades = intent.getIntExtra("Unidades",0)
        val apto = intent.getBooleanExtra("Apto", true)
        val aptoOld : String

        if (apto == true){
            aptoOld = "Es apto para niños"
        } else{
            aptoOld = "NO es apto para niños"
        }

        ed_nomFar.hint = nomFar
        ed_nomMed.hint = nomMed
        ed_precio.hint = precio.toString()
        ed_fecha.hint = fecha
        ed_unidades.hint = unidades.toString()
        ed_apto.hint = aptoOld

        btn_guardar.setOnClickListener { obtenerMedActualizacion(posicion) }
        btn_cancelar.setOnClickListener { volver() }

    }

    fun obtenerMedActualizacion(posicion : Int){
        val url = urlPrincipal + "/medicamento" + posicion
        var nombreFarAct : String
        var nombreMedAct : String
        var precioAct : Float
        var fechaAct : String
        var unidadesAct : Int
        var aptoAct : Boolean
        var apt : String

        if (ed_nomFar.length() == 0)
            nombreFarAct = ed_nomFar.hint.toString()
        else
            nombreFarAct = ed_nomFar.text.toString()

        if (ed_nomMed.length() == 0)
            nombreMedAct = ed_nomMed.hint.toString()
        else
            nombreMedAct = ed_nomMed.text.toString()

        if (ed_precio.length() == 0)
            precioAct = ed_precio.hint.toString().toFloat()
        else
            precioAct = ed_precio.text.toString().toFloat()

        if (ed_fecha.length() == 0)
            fechaAct = ed_fecha.hint.toString()
        else
            fechaAct = ed_fecha.text.toString()

        if (ed_unidades.length() == 0)
            unidadesAct = ed_unidades.hint.toString().toInt()
        else
            unidadesAct = ed_unidades.text.toString().toInt()

        if (ed_apto.length() == 0)
            apt = ed_apto.hint.toString()
        else
            apt = ed_apto.text.toString()

        if (apt.equals("Es apto para niños"))
            aptoAct = true
        else
            aptoAct = false


        listaMedica[posicion].nombreFarmacia = nombreFarAct
        listaMedica[posicion].nombreMedicamento = nombreMedAct
        listaMedica[posicion].precioMedicamento = precioAct
        listaMedica[posicion].fechaMedicamento = fechaAct
        listaMedica[posicion].unidadesMedicamento = unidadesAct
        listaMedica[posicion].prevencion = aptoAct

        val parametrosMedicamento = listOf(
            "nombreFarmacia" to nombreFarAct,
            "nombreMedicamento" to nombreMedAct,
            "precioMedicamento" to precioAct,
            "fechaMedicamento" to fechaAct,
            "unidadesMedicamento" to unidadesAct,
            "prevencion" to aptoAct
        )

        url.httpPut(parametrosMedicamento)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Nombre: ${error}")
                    }
                    is Result.Success -> {
                        val medicamentoString = result.get()
                        Log.i("http-klaxon1", "${medicamentoString}")
                    }
                }
            }

        val intentActuali = Intent(
            this,
            GestionarMedicamentoActivity :: class.java
        )
        startActivity(intentActuali)

    }

    fun volver(){
        val intentActuali = Intent(
            this,
            GestionarMedicamentoActivity :: class.java
        )
        startActivity(intentActuali)
    }
}
