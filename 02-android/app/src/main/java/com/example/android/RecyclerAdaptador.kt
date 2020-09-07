package com.example.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class RecyclerAdaptador(
    private val listaUsuarios: List<UsuarioHttp>,
    private val contexto: RecyclerViewActivity,
    private val recyclerView: androidx.recyclerview.widget.RecyclerView
) : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerAdaptador.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdaptador.MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.adaptador_persona,
                parent,
                false
            )
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    inner class MyViewHolder(view: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        // dentro de este adaptador queremos tener varios text view
        // Nos permiten iterar
        val nombreTextView: TextView
        val cedulaTextView: TextView
        val likesTextView: TextView
        val accionButton: Button

        var numeroLikes = 0
        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            accionButton = view.findViewById(R.id.btn_accion)
            likesTextView = view.findViewById(R.id.tv_likes)
            accionButton.setOnClickListener {
                this.anadirLike()
            }
        }

        fun anadirLike(){
            this.numeroLikes = this.numeroLikes + 1
            likesTextView.text = this.numeroLikes.toString()
            contexto.anadirLikesEnActividad(1)
        }

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val usuario= listaUsuarios[position]
        holder.nombreTextView.text = usuario.nombre
        holder.cedulaTextView.text = usuario.cedula
        holder.accionButton.text = "Like ${usuario.nombre}"
        holder.likesTextView.text = "0"
    }





}