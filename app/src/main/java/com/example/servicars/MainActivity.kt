package com.example.servicars

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val accesoAdmin = "admin"

    fun cambiarActividad (view: View){

        val editUsuario = findViewById<EditText>(R.id.UsuarioEditText)
        val editContraseña = findViewById<EditText>(R.id.ContraseñaEditText)

        val textUsuario = editUsuario.text.toString()
        val textContraseña = editContraseña.text.toString()

        if(textUsuario.isEmpty()){
            editUsuario.error = "Ingrese el usuario"
            editUsuario.requestFocus()
            return
        }

        if(textContraseña.isEmpty()){
            editContraseña.error = "Ingrese la Contraseña"
            editContraseña.requestFocus()
            return
        }

        if(textUsuario==accesoAdmin && textContraseña==accesoAdmin){
            val secondAct = Intent(this, SecondActivity::class.java)
            startActivity(secondAct)
        }else{
            Toast.makeText(this,"Contraseña incorrecta", Toast.LENGTH_SHORT).show()
        }

    }

}