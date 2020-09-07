package com.example.android

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_envia_parametros.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

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


        btn_intent_respuesta.setOnClickListener {
            irAIntentConRespuesta()
        }

        btn_inten_implicito.setOnClickListener {
            enviarIntentConRespuesta()
        }

        btn_resp_propia.setOnClickListener {
            enviarIntenConRespuestaPropia()
        }

        btn_http.setOnClickListener {
            irHTTP()
        }

        btn_recycler.setOnClickListener {
            abrirRecyclerViewActivity()
        }

        btn_mapa.setOnClickListener {
            abrirMapaActivity()
        }

    }

    fun abrirMapaActivity(){
        val intentExplicito = Intent(
            this,
            MapsActivity::class.java
        )
        startActivity(intentExplicito)
    }

    fun abrirRecyclerViewActivity(){
        val intentExplicito = Intent(
            this,
            RecyclerViewActivity::class.java
        )
        startActivity(intentExplicito)
    }


    fun irHTTP(){
        val intent = Intent(
            this,
            HttpActivity :: class.java
        )
        startActivity(intent)
    }


    fun enviarIntenConRespuestaPropia(){
        val intentConRespuesta = Intent(
            this,
            IntentEnviaParametros ::class.java
        )
        //this.startActivityForResult(intentConRespuesta,304)
        startActivityForResult(intentConRespuesta,305)

    }

    fun enviarIntentConRespuesta(){
        val intentConRespuesta = Intent(
            Intent.ACTION_PICK,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        //this.startActivityForResult(intentConRespuesta,304)
        startActivityForResult(intentConRespuesta,304)

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ){
        super.onActivityResult(requestCode,resultCode, data)
        when(resultCode){
            Activity.RESULT_OK ->{
                Log.i("Resultado", "Ok")
                when(requestCode){
                    305 -> {
                        if(data != null){
                            val nombre = data.getStringExtra("nombre")
                            val edad = data.getIntExtra("edad",0)
                            Log.i("R esultado", "Nombre: ${nombre} Edad: ${edad}")
                        }
                    }
                }

            }
            Activity.RESULT_CANCELED -> {
                Log.i("Resultado", ":(")
            }
        }

        /*val uri = data?.data
        if (uri != null) {
            val cursor = contentResolver.query(
                uri,
                null,
                null,
                null,
                null,
                null
            )
            cursor?.moveToFirst()
            val indiceTelefono = cursor?.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.NUMBER
            )
            val telefono = cursor?.getString(indiceTelefono!!)
            cursor?.close()
            Log.i("resultado", "Telefono: ${telefono}")
        }*/


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


    //Clase 20/07/2020
    fun irAIntentConRespuesta(){
        //parte del envío de respuesta
        val intentExplicito = Intent(
            this,
            IntentEnviaParametros :: class.java
        )
        intentExplicito.putExtra("numero", 2) // De esta manera enviamos valores primitivos
        intentExplicito.putExtra("numero1", 3)
        intentExplicito.putExtra("numero2", 4)
        intentExplicito.putExtra("numero3", 5)
        intentExplicito.putExtra("numero4", 6)
        intentExplicito.putExtra("numero5", 7)

        //clase 06/08/2020
        val jonathan = Usuario("Jonathan",24, Date(),400.00)

        val picachu = Mascota("Picachu", jonathan)

        val arregloMascotas = arrayListOf<Mascota>(picachu)

        intentExplicito.putExtra("picachu", picachu)
        intentExplicito.putExtra("arregloMascotas", arregloMascotas)


        startActivity(intentExplicito)

    }

    //clase 24/08/2020






    /*override fun OnActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ){

    }*/

    /*override fun OnActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ){
        super.onActivityResult(requestCode,resultCode, data)
        when(resultCode){
            Activity.RESULT_OK ->{
                Log.i("Resultado", "Ok")
            }
            Activity.RESULT_CANCELED -> {
                Log.i("Resultado", ":(")
            }
        }
    }*/






}
