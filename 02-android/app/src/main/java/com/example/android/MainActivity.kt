package com.example.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //aquí es donde hacemos todas las configuraciones de las aplicaciones
        setContentView(R.layout.activity_main)
        Log.i("Activity","OnCreate")
        btn_ciclo_vida
            .setOnClickListener { boton ->
                //this.irCicloDeVida()
                irCicloDeVida()
            }

        btn_list_view
            .setOnClickListener { boton ->
                irListView()
            }
    }

    //funcion para ir a una actividad diferente
    fun irCicloDeVida(){
        val intentExplicito = Intent(
            this,
            CicloVida::class.java
        )
        //this.startActivity(intentExplicito)
        startActivity(intentExplicito) //metodo dentro de la clase
    }

    fun irListView(){
        val intentExplicito = Intent(
            this,
            BListViewActivity::class.java
        )
        //this.startActivity(intentExplicito)
        startActivity(intentExplicito) //metodo dentro de la clase
    }



}
