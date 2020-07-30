package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ver_farmacia.*

class VerFarmaciaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_farmacia)

        val nombre = intent.getStringExtra("Nombre Farmacia")
        val direccion = intent.getStringExtra("Direccion Farmacia")
        val trabajadores = intent.getIntExtra("Num Trabajadores", 0)
        val compra = intent.getFloatExtra("Compra", 0f)
        val horario = intent.getBooleanExtra("Atencion", true)
        var atencion = ""
        if(horario == true){
            atencion = "Atiende las 24 horas"
        } else{
            atencion = "No atiende las 24 horas"
        }

        Log.i("recuperar","el nombre es: ${nombre}")
        Log.i("recuperar","la direccion es: ${direccion}")
        Log.i("recuperar","la direccion es: ${horario}")
        Log.i("recuperar","la direccion es: ${atencion}")

        tv_nombre.text = nombre.toString()
        tv_direccion.text = direccion.toString()
        tv_trabajadores.text = trabajadores.toString()
        tv_compra.text = compra.toString()
        tv_atencion.text = atencion.toString()



    }


}
