package com.example.servicars

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.servicars.ui.dashboard.DashboardFragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("msg", "Ok")
        analytics.logEvent("Inicializacion", bundle)
    }

    fun registrarUsuario(view: View) {

        val editUsuario = findViewById<EditText>(R.id.UsuarioEditText)
        val editContraseña = findViewById<EditText>(R.id.ContraseñaEditText)

        val textUsuario = editUsuario.text.toString()
        val textContraseña = editContraseña.text.toString()

        if (textUsuario.isEmpty()) {
            editUsuario.error = "Ingrese el usuario"
            editUsuario.requestFocus()
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(textUsuario).matches()) {
            editUsuario.error = "Direccion de correo no valida"
            editUsuario.requestFocus()
            return
        }

        if (textContraseña.isEmpty()) {
            editContraseña.error = "Ingrese la Contraseña"
            editContraseña.requestFocus()
            return
        }
        if (textContraseña.length < 6) {
            editContraseña.error = "La contraseña debe tener minimo 6 caracteres"
            editContraseña.requestFocus()
            return
        }

        val login =
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(textUsuario, textContraseña)
        login.addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                val secondAct = Intent(this, SecondActivity::class.java)
                startActivity(secondAct)
                //TODO: Limpiar inputs.
            } else {
                editContraseña.error = it.exception?.message.toString()
            }
        }

    }

    fun iniciarSesion(view: View) {

        val editUsuario = findViewById<EditText>(R.id.UsuarioEditText)
        val editContraseña = findViewById<EditText>(R.id.ContraseñaEditText)

        val textUsuario = editUsuario.text.toString()
        val textContraseña = editContraseña.text.toString()

        if (textUsuario.isEmpty()) {
            editUsuario.error = "Ingrese el usuario"
            editUsuario.requestFocus()
            return
        }
        if (textContraseña.isEmpty()) {
            editContraseña.error = "Ingrese la Contraseña"
            editContraseña.requestFocus()
            return
        }
        if (textContraseña.length < 6) {
            editContraseña.error = "La contraseña debe tener minimo 6 caracteres"
            editContraseña.requestFocus()
            return
        }

        val login =
            FirebaseAuth.getInstance().signInWithEmailAndPassword(textUsuario, textContraseña)
        login.addOnCompleteListener {
            if (it.isSuccessful) {
                val secondAct = Intent(this, SecondActivity::class.java)
                startActivity(secondAct)
                //TODO: Limpiar inputs.
            } else {
                val messageError = it.exception?.message
                Log.e("Mensajes de Error ", messageError.toString())
                when (messageError) {
                    "There is no user record corresponding to this identifier. The user may have been deleted." -> editContraseña.error =
                        "Este usuario no se encuentra registrado"
                    "The password is invalid or the user does not have a password." -> editContraseña.error =
                        "Usuario o contraseña incorrecta"
                }
            }
        }

    }

}