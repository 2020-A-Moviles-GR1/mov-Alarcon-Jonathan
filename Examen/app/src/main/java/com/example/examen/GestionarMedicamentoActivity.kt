package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_gestionar_medicamento.*

class GestionarMedicamentoActivity : AppCompatActivity() {

    var listaMedi = arrayListOf<MedicamentoAtributos>()
    var nombresMed = arrayListOf<String>()
    var posicion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestionar_medicamento)

        listaMedi = ServicioBDDMemoria.listaMedicamentos
        Log.i("MediGestion","La lista actualizacion es: ${listaMedi}")

        for (e in listaMedi){
            nombresMed.add(e.nombreMedicamento)
        }

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            nombresMed
        )

        lv_medicamentos.adapter = adaptador

        lv_medicamentos.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            posicion = position
        }

        btn_verMedi.setOnClickListener { verMedicamento(posicion) }
        btn_actuaMedi.setOnClickListener { actualizarMedi(posicion) }
        btn_elimMedi.setOnClickListener { eliminarMedicamento(adaptador,posicion) }
        btn_atras.setOnClickListener { irAtras() }

    }

    fun verMedicamento(pos : Int){

        val intentVer = Intent(
            this,
            VerMedicamentoActivity :: class.java
        )

        intentVer.putExtra("Nombre Far", listaMedi[pos].nombreFarmacia)
        intentVer.putExtra("Nombre Med", listaMedi[pos].nombreMedicamento)
        intentVer.putExtra("Precio", listaMedi[pos].precioMedicamento)
        intentVer.putExtra("Fecha", listaMedi[pos].fechaMedicamento)
        intentVer.putExtra("Unidades", listaMedi[pos].unidadesMedicamento)
        intentVer.putExtra("Apto", listaMedi[pos].prevencion)
        startActivity(intentVer)
    }

    fun actualizarMedi(pos : Int){

        val intentActualizar = Intent(
            this,
            ActualizarMedicamentoActivity :: class.java
        )

        intentActualizar.putExtra("Indice", pos)
        intentActualizar.putExtra("Nombre Far", listaMedi[pos].nombreFarmacia)
        intentActualizar.putExtra("Nombre Med", listaMedi[pos].nombreMedicamento)
        intentActualizar.putExtra("Precio", listaMedi[pos].precioMedicamento)
        intentActualizar.putExtra("Fecha", listaMedi[pos].fechaMedicamento)
        intentActualizar.putExtra("Unidades", listaMedi[pos].unidadesMedicamento)
        intentActualizar.putExtra("Apto", listaMedi[pos].prevencion)
        startActivity(intentActualizar)

    }

    fun eliminarMedicamento(adaptador:ArrayAdapter<String>,pos: Int){

        val mediBorrar = listaMedi[pos].nombreMedicamento.toString()
        listaMedi.removeAt(pos)
        adaptador.remove(mediBorrar)
        adaptador.notifyDataSetChanged()
    }

    fun irAtras(){
        val intentAtras = Intent(
            this,
            MainActivity :: class.java
        )
        startActivity(intentAtras)
    }

}
