package com.example.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_vida.*

class CicloVida : AppCompatActivity() {


    var numeroActual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
        Log.i("Activity","OnCreate")
        btn_aniadir.setOnClickListener {
            sumarValor()
        }
    }

    fun sumarValor(){

        numeroActual = numeroActual + 1
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
}
