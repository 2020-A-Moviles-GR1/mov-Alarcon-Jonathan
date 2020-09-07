package com.example.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    var numeroLikes = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val listaUsuarios = arrayListOf<UsuarioHttp>()
        listaUsuarios.add(
            UsuarioHttp(1, 123123123,123123123, "1718654872", "Jonathan","j@j.com",
                "Soltero", "123Pasword", arrayListOf<PokemonHttp>())
        )
        listaUsuarios.add(
            UsuarioHttp(2, 123123123,123123123, "1718654825", "Dayana","d@d.com",
                "Soltera", "123Pasword", arrayListOf<PokemonHttp>())
        )
        listaUsuarios.add(
            UsuarioHttp(3, 123123123,123123123, "1718654884", "Melany","m@m.com",
                "Soltero", "123Pasword", arrayListOf<PokemonHttp>())
        )

        iniciarRecyclerView(
            listaUsuarios,
            this,
            rv_usuarios
        )

    }
    fun iniciarRecyclerView(
        lista: List<UsuarioHttp>,
        actividad: RecyclerViewActivity,
        recycler_view: androidx.recyclerview.widget.RecyclerView
    ){
        val adaptadorUsuario = RecyclerAdaptador(
            lista,
            actividad,
            recycler_view
        )
        rv_usuarios.adapter = adaptadorUsuario
        rv_usuarios.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_usuarios.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptadorUsuario.notifyDataSetChanged()
    }

    fun anadirLikesEnActividad(numero: Int){
        this.numeroLikes = this.numeroLikes + numero
        tv_titulo_rv.text = numeroLikes.toString()

    }
}
