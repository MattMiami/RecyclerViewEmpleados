package com.example.empleadosrecyclerview

import android.app.VoiceInteractor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val cola = Volley.newRequestQueue(this)
        var url = "http://iesayala.ddns.net/antoniomateos/Empleados.php"


        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
            val empleados = response.getJSONArray("Empleados")
            Log.d("Json", empleados.toString())
            recyclerView.adapter = EmpleadosAdapter(empleados)
        },
        Response.ErrorListener { error -> Log.d("JSON", error.toString()) })


        cola.add(jsonObjectRequest)




    }
}