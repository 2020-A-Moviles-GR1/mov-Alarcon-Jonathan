package com.example.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import kotlinx.android.synthetic.main.activity_http.*
import com.github.kittinunf.result.Result

class HttpActivity : AppCompatActivity() {

    val urlPrincipal = "http://192.168.1.180:1337"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        btn_obtener.setOnClickListener {
            obtenerUsuarios()
        }

        btn_crear.setOnClickListener {
            crearUsuario()
        }
    }

    fun crearUsuario(){
        val url = urlPrincipal + "/Usuario"
        // Esta es una lista de clave valor

        val parametrosUsuario = listOf(
            "cedula" to "12345678910",
            "nombre" to "Pepito",
            "correo" to "hola51@epn.edu.com",
            "estadoCivil" to "Casado",
            "password" to "Pass123456"
        )

        url.httpPost(parametrosUsuario)
            .responseString{ req, res, result ->
                when(result){
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                        Log.i("http-klaxon1", "${usuarioString}")
                    }
                }
            }
    }



    fun obtenerUsuarios(){

        val pokemonString = """
        {
            "createdAt": 1597671444841,
            "updatedAt": 1597672206086,
            "id": 1,
            "nombre": "Pikachu",
            "usuario": 1
        }
        """.trimIndent()

        val pokemonInstanciado = Klaxon().parse<PokemonHttp>(pokemonString)

        if(pokemonInstanciado != null){
            Log.i("http-klaxon", "Nombre: ${pokemonInstanciado.nombre}")
            Log.i("http-klaxon", "Creado: ${pokemonInstanciado.createdAt}")
            Log.i("http-klaxon", "Actualizado: ${pokemonInstanciado.fechaActualizacion}")
        }


        val url = urlPrincipal + "/Usuario"
        url
            .httpGet()
            .responseString{
                request, response, result ->
                when(result){
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http-klaxon", "Data: ${data}")
                        Log.i("http-klaxon", "Data: ${pokemonInstanciado}")

                        val usuarios = Klaxon().parseArray<UsuarioHttp>(data)
                        if(usuarios != null){
                            usuarios.forEach {
                                Log.i("http-klaxon1", "Nombre: ${it.nombre}" + " Correo: ${it.correo}")
                                /*if(it.pokemons.size > 0){
                                    it.pokemons.forEach {
                                        Log.i("http-klaxon1", "Nombre: ${it.nombre}")
                                    }
                                }*/
                            }

                        }

                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-klaxon", "Error: ${ex.message}")
                    }
                }
            }


        val urlSecundaria = urlPrincipal + "/pokemon"
        urlSecundaria.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    val data2 = result.get()
                    val pokemons = Klaxon().converter(PokemonHttp.myConverter)
                        .parseArray<PokemonHttp>(data2)
                    if (pokemons!!.size != 0) {
                        pokemons!!.forEach {
                            Log.i("http-klaxon", "USUARIO:${it.usuario.toString()}")
                            println(it.usuario?.javaClass?.getSimpleName())


                        }
                    }
                }
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http_klaxon", "error:${ex.message}")
                }

            }
        }



    }



}
