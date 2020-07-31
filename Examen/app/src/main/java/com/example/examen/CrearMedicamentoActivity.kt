package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_crear_medicamento.*

class CrearMedicamentoActivity : AppCompatActivity() {

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

    }

    fun irPantallaAtras(){
        val intentAtras = Intent(
            this,
            GestionFarmaciaActivity :: class.java
        )
        startActivity(intentAtras)
    }

}
