package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_buscar_farmacia.*

class GestionFarmaciaActivity : AppCompatActivity() {

    var listaFarmacias1 = arrayListOf<FarmaciaAtributos>()
    val nombresFar = arrayListOf<String>()
    val eleccion = arrayListOf<FarmaciaAtributos>()
    var posicion = 0
    var listaMedicamentos = arrayListOf<MedicamentoAtributos>()
    val urlPrincipal = "http://192.168.1.105:1337"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_farmacia)

        listaFarmacias1 = ServicioBDDMemoria.listaFarmacias
        listaMedicamentos = ServicioBDDMemoria.listaMedicamentos
        Log.i("Volver","La lista actualizacion es: ${listaFarmacias1}")
        //listaFarmacias1.add(FarmaciaAtributos(1,"Sana Sana","America",25,1.5f,true))
        //listaFarmacias1.add(FarmaciaAtributos(2,"Medicity","10 de Agosto",55,0.23f,false))

        for (e in listaFarmacias1){
            nombresFar.add(e.nombreFarmacia)
            Log.i("nombres","el nombre es: ${e.nombreFarmacia}")
        }

        //**********************esto si vale**************

        val adaptador: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            nombresFar

        )

        //**********************esto es una prueba, solo el adaptador**************

        /*val adaptador: ArrayAdapter<FarmaciaAtributos> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,

        )*/

        lv_farmacia.adapter = adaptador

        lv_farmacia.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            //eleccion[position]
            Log.i("indice","Posicion: $position")
            posicion = position
            Log.i("indice","El indice es: ${posicion}")
            //Log.i("indice","Posicion: $position, nombre: ${eleccion[position].nombreFarmacia}")
            //Log.i("indice","Posicion: $position, nombre: ${eleccion[position].direccionFarmacia}")
            //Log.i("indice","Posicion: $position, nombre: ${eleccion[position].compra}")
        }

        btn_caracteristicasFar.setOnClickListener { verFarmacia(posicion) }
        btn_eliminarFar.setOnClickListener { eliminarfarmacia(adaptador,posicion) }
        btn_actualizarFar.setOnClickListener { actualizarFarmacia(posicion) }
        btn_crearMedicamento.setOnClickListener { irCrearMedicamento(posicion) }
        btn_regresar.setOnClickListener { regresar() }
        btn_ubicacion.setOnClickListener { abrirMapaActivity() }

        /*
        val adaptador: ArrayAdapter<Entrenador> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, //Nombre Layout
            listaEntrenadores //Lista
        )

        lv_numeros
            .onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.i("list-view","Posicion $position")
            }
         */

    }

    fun abrirMapaActivity(){
        val intent = Intent(
            this,
            MapsActivity::class.java
        )
        intent.putStringArrayListExtra("Farmacias", nombresFar)
        startActivity(intent)
    }

    fun verFarmacia(pos:Int){

        val intentExplicito = Intent(
            this,
            VerFarmaciaActivity :: class.java
        )
        intentExplicito.putExtra("Nombre Farmacia",listaFarmacias1[pos].nombreFarmacia)
        intentExplicito.putExtra("Direccion Farmacia", listaFarmacias1[pos].direccionFarmacia)
        intentExplicito.putExtra("Num Trabajadores", listaFarmacias1[pos].numeroTrabajadores)
        intentExplicito.putExtra("Compra", listaFarmacias1[pos].compra)
        intentExplicito.putExtra("Atencion", listaFarmacias1[pos].atencion)
        startActivity(intentExplicito)

        Log.i("Far","El nombre es: ${listaFarmacias1[pos].nombreFarmacia}")
        Log.i("Far","La direccion es: ${listaFarmacias1[pos].direccionFarmacia}")
    }


    fun actualizarFarmacia(pos:Int){
        val intentExplicito = Intent(
            this,
            ActualizarFarmaciaActivity :: class.java
        )
        intentExplicito.putExtra("Indice",pos)
        intentExplicito.putExtra("Nombre Farmacia",listaFarmacias1[pos].nombreFarmacia)
        intentExplicito.putExtra("Direccion Farmacia", listaFarmacias1[pos].direccionFarmacia)
        intentExplicito.putExtra("Num Trabajadores", listaFarmacias1[pos].numeroTrabajadores)
        intentExplicito.putExtra("Compra", listaFarmacias1[pos].compra)
        intentExplicito.putExtra("Atencion", listaFarmacias1[pos].atencion)
        startActivity(intentExplicito)
    }


    fun eliminarfarmacia(adaptador:ArrayAdapter<String>,pos: Int){

        val url = urlPrincipal + "/farmacia" + pos
        val nombreBorrar = listaFarmacias1[pos].nombreFarmacia.toString()
        var contador = 0
        for (e in listaMedicamentos){

            if (listaFarmacias1[pos].nombreFarmacia.equals(e.nombreFarmacia)){
                listaMedicamentos.removeAt(contador)
            }
            contador = contador + 1
        }
        Log.i("borrado","La lista de medicamentos es: ${listaMedicamentos}")
        Log.i("borrado","el nombre a borrar es: ${nombreBorrar}")
        listaFarmacias1.removeAt(pos)
        Log.i("borrado","la lista nueva es: ${listaFarmacias1}")
        adaptador.remove(nombreBorrar)

        url.httpDelete()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure->{
                        val error = result.getException()
                        Log.i("http-klaxon", "Nombre: ${error}")
                    }
                    is Result.Success->{
                        val farmaciaString = result.get()
                        Log.i("http-klaxon1", "${farmaciaString}")
                    }
                }
            }

        /*for (e in listaFarmacias1){
            nombresFar.add(e.nombreFarmacia)
            Log.i("nombres","el nombre es: ${e.nombreFarmacia}")
        }
        lv_farmacia.adapter = adaptador*/
        //ServicioBDDMemoria.listaFarmacias = listaFarmacias1
        adaptador.notifyDataSetChanged()
    }

    fun irCrearMedicamento(posFar : Int){
        //val nombreFarm = listaFarmacias1[posFar].nombreFarmacia
        val intent = Intent(
            this,
            CrearMedicamentoActivity :: class.java
        )
        intent.putExtra("Nombre Far", listaFarmacias1[posFar].nombreFarmacia )
        startActivity(intent)
    }

    fun regresar(){
        val intent = Intent(
            this,
             MainActivity :: class.java
        )

        startActivity(intent)
    }


}
