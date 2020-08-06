package com.example.android

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_envia_parametros.*

class IntentEnviaParametros : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_envia_parametros)


        //Clase 20/07/2020
        //Aquí vamos a recibir los parámetros
                //hay una clase llamada Intent
        //toca mandarle también un valor por defecto
        val numeroEncontrado = intent.getIntExtra("numero", 0)// el intent existe dentro de la clase
        val numeroEncontrado1 = intent.getIntExtra("numero1", 0)
        val numeroEncontrado2 = intent.getIntExtra("numero2", 0)
        val numeroEncontrado3 = intent.getIntExtra("numero3", 0)
        val numeroEncontrado4 = intent.getIntExtra("numero4", 0)
        val numeroEncontrado5 = intent.getIntExtra("numero5", 0)

        if (numeroEncontrado !=0 ){
            Log.i("intents","El numero encontrado es: ${numeroEncontrado}")
            Log.i("intents","El numero encontrado es: ${numeroEncontrado1}")
            Log.i("intents","El numero encontrado es: ${numeroEncontrado2}")
            Log.i("intents","El numero encontrado es: ${numeroEncontrado3}")
            Log.i("intents","El numero encontrado es: ${numeroEncontrado4}")
            Log.i("intents","El numero encontrado es: ${numeroEncontrado5}")
        }

        //Tenemos que poner una validación por que puede ser nulo
        val textoCompartido: String? = intent.getStringExtra(Intent.EXTRA_TEXT)
        if(textoCompartido != null){
            Log.i("intents","el texto es ${textoCompartido}")
        }


        //clase 06/08/2020

        val picachu = intent.getParcelableExtra<Mascota>("picachu")
        if(picachu != null){
            Log.i("parcelable", "${picachu.nombre} ${picachu.duenio?.nombre}")
        }

        val arregloMascota = intent.getParcelableArrayListExtra<Mascota>("arregloMascotas")
        if(arregloMascota != null){
            /*arregloMascota.forEach(
                Log.i("parcelable", "EN ARREGLO")
                        //Log.i("parcelable", "${picachu.nombre} ${picachu.duenio?.nombre}")

            )*/
            arregloMascota.forEach {
                if(it != null){
                    Log.i("parcelable", "EN ARREGLO")
                    Log.i("parcelable", "${it.nombre} ${it.duenio?.nombre}")
                }

            }
        }



        //¿Como volver a la pantalla anterior

        btn_devolver_respuesta.setOnClickListener {
            //metodo de la clase
            //this.finish()
            finish() // con esta parte se regresa a la pantalla
        }

        btn_resp_aceptar.setOnClickListener {
            val nombre = "Jonathan"
            val edad = 24
            val intentRespuesta = Intent()
            intentRespuesta.putExtra("nombre",nombre)
            intentRespuesta.putExtra("edad",edad)
            setResult(
                Activity.RESULT_OK,
                intentRespuesta
            )
            finish()
        }

        btn_resp_cancelar.setOnClickListener {
            val intentCancelado = Intent()
            setResult(
                Activity.RESULT_CANCELED,
                intentCancelado
            )
            finish()
        }








    }







}
