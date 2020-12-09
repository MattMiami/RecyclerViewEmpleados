package com.example.empleadosrecyclerview

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_empleados.*
import org.json.JSONObject
/*
Clase para contactar con el empleado seleccionado en el RecyclerView
 */
class EmpleadosAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleados)

        //Recogemos la informacion del intent con el objeto empleado
        val bundle: Bundle?  = intent.extras
        if (bundle!=null){
            val empleadosString = bundle!!.getString("empleado")
            val empleado = JSONObject(empleadosString)
            val name =  empleado["Nombre"]
            val dni = empleado["DNI"]
            val surname = empleado["Apellido"]
            val phone = empleado["Telefono"]
            val foto = empleado["Foto"]

            //Para la foto
            Glide.with(this).load(foto).into(imageView2)
            textView.text = "Empleado: "+name+" "+surname+"\n"+"DNI: "+dni+"\nTeléfono: "+phone

            //Enviar email
            btOk.setOnClickListener{
                Toast.makeText(this, "Mensaje enviado a " + name +" Contacto: "+ phone, Toast.LENGTH_SHORT).show()}
            btCancel.setOnClickListener{
                etml.text.clear()
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()}

            //Para contactar con el empleado
            var phone1:String?=""
            btCall.setOnClickListener {
                phone1 = phone.toString().trim()
                val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone1, null))
                startActivity(intent)
            }
        }
    }
}