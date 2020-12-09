package com.example.empleadosrecyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.bumptech.glide.module.AppGlideModule
import kotlinx.android.synthetic.main.item_empleado.view.*
import org.json.JSONArray
import org.json.JSONObject

class EmpleadosAdapter(private val empleados: JSONArray)
    : RecyclerView.Adapter<EmpleadosAdapter.ViewHolder>(){


    // Creamos el modelo para cada item del recyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpleadosAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_empleado, parent, false)
        return ViewHolder(view)
    }

    // Llamamos a las vistas que tengamos en ese modelo
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.nameTextView
        val imageView: ImageView = view.imageView
    }

    //Enlazamos las vistas con los datos
    override fun onBindViewHolder(holder: EmpleadosAdapter.ViewHolder, position: Int) {
        //Informacion del empleado en texto
        val empleado: JSONObject = empleados.getJSONObject(position)
        holder.nameTextView.text = empleado["Nombre"].toString() + " " + empleado["Apellido"].toString()+"\n"+ empleado["DNI"].toString()+"\n"+ empleado["Telefono"].toString()+"\n"+ empleado["Direccion"].toString()
        //La foto del empleado
        val uri: String = empleado["Foto"].toString()
        val name: String = empleado["Nombre"].toString()
        val num: String = empleado["Telefono"].toString()
        Glide.with(holder.itemView.context).load(uri).into(holder.imageView)
        //El toast con la info rápida
        holder.nameTextView.setOnClickListener{Toast.makeText(holder.itemView.context, "Empleado: "+name+"\nTelefono de Contacto: "+num+" ", Toast.LENGTH_SHORT).show()}


        //Para la actividad del empleado, metemos la informacion del empleado seleccionado en un intent
        holder.imageView.setOnClickListener {
            val intent = Intent(holder.itemView.context, EmpleadosAct::class.java)
            intent.putExtra("empleado",empleados[position].toString())
            holder.itemView.context.startActivity(intent)
        }


    }


    //Recorre la lista
    override fun getItemCount(): Int = empleados.length()


}

