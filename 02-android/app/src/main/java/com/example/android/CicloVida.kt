package com.example.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_vida.*

class CicloVida : AppCompatActivity() {


    var numeroActual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
        Log.i("Activity","OnCreate")
        numeroActual = ServicioBDDMemoria.numero
        if (numeroActual != 0){
            tv_numero.text = numeroActual.toString()
        }


        btn_aniadir.setOnClickListener {
            sumarValor()
        }
    }

    fun sumarValor(){

        numeroActual = numeroActual + 1
        ServicioBDDMemoria.anadirNumero()
        tv_numero.text = numeroActual.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.i("Activity","OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity","OnResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Activity","OnRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity","OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity","OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity","OnDestroy")
    }

    //clase 09/07/2020

    override fun onSaveInstanceState(outState: Bundle?) {
        Log.i("Activity","onSaveInstanceState")
        outState?.run {
            //dentro de estos métodos no podemos mandar a guardar cualquier cosa
            //se puede guardar datos primitivos y clases
            putInt("numeroActualGuardado", numeroActual) //con esta línea estamos guardando el código
        }
        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("Activity","onRestoreInstanceState")
        //vamos a intentar recuperar el valor
        val valorRecuperado = savedInstanceState?.getInt("numeroActualGuardado") //con esta línea ya tenemos guardado el valor
        if(valorRecuperado != null){
            this.numeroActual = valorRecuperado
            tv_numero.text = this.numeroActual.toString()
        }
    }




}
