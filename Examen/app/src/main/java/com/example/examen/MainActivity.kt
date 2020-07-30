package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_farmacia.setOnClickListener { irFarmacia() }
    }

    fun irFarmacia(){
        val intentExplicito = Intent(
            this,
            Farmacia_act :: class.java
        )
        startActivity(intentExplicito)
    }
}
