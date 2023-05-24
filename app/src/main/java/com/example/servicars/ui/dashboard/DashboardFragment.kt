package com.example.servicars.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.servicars.Order
import com.example.servicars.databinding.FragmentDashboardBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.time.LocalDate

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var btnguardar: Button
    private lateinit var btnCancelar: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val currentUsuario = FirebaseAuth.getInstance().currentUser
        val emailCurrentUsuario = currentUsuario?.email
        Log.i("Current User Firebase", emailCurrentUsuario.toString())

        val textView: TextView = binding.textDashboard
        val nombreClienteInput: TextInputLayout = binding.NombreClienteInputLayout
        val idClienteInput: TextInputLayout = binding.IDClienteInputLayout
        val telefonoClienteInput: TextInputLayout = binding.telefonoClienteInputLayout
        val correoClienteInput: TextInputLayout = binding.correoClienteInputLayout

        val textViewDatosAuto: TextView = binding.textDatosAuto
        val marcaAutoInput: TextInputLayout = binding.marcaAutoInputLayout
        val modeloAutoInput: TextInputLayout = binding.modeloAutoInputLayout
        val anioAutoInput: TextInputLayout = binding.anioAutoInputLayout
        val matriculaAutoInput: TextInputLayout = binding.matriculaAutoInputLayout
        val fallaAutoInput: TextInputLayout = binding.fallaAutoInputLayout

        val guardarBtn: Button = binding.guardarBtn
        val cancelarBtn: Button = binding.cancelarBtn

        //Funciones
        fun limpiarInputs(view: View) {
            val editNombreCliente = binding.nombreClienteEditText
            val editIdCliente = binding.idClienteEditText
            val editTelefonoCliente = binding.telefonoClienteEditText
            val editCorreoCliente = binding.correoClienteEditText

            val editMarcaAuto = binding.marcaAutoEditText
            val editModeloAuto = binding.modeloAutoEditText
            val editAnioAuto = binding.anioAutoEditText
            val editMatriculaAuto = binding.matriculaAutoEditText
            val editFallaAuto = binding.fallaAutoEditText

            editNombreCliente.setText("")
            editIdCliente.setText("")
            editTelefonoCliente.setText("")
            editCorreoCliente.setText("")
            editMarcaAuto.setText("")
            editModeloAuto.setText("")
            editAnioAuto.setText("")
            editMatriculaAuto.setText("")
            editFallaAuto.setText("")
        }

        fun guardarDatos(view: View) {
            val editNombreCliente = binding.nombreClienteEditText
            val editIdCliente = binding.idClienteEditText
            val editTelefonoCliente = binding.telefonoClienteEditText
            val editCorreoCliente = binding.correoClienteEditText
            val editMarcaAuto = binding.marcaAutoEditText
            val editModeloAuto = binding.modeloAutoEditText
            val editAnioAuto = binding.anioAutoEditText
            val editMatriculaAuto = binding.matriculaAutoEditText
            val editFallaAuto = binding.fallaAutoEditText

            val textNombreCliente = editNombreCliente.text.toString()
            val textIdCliente = editIdCliente.text.toString()
            val textTelefonoCliente = editTelefonoCliente.text.toString()
            val textCorreoCliente = editCorreoCliente.text.toString()
            val textMarcaAuto = editMarcaAuto.text.toString()
            val textModeloAuto = editModeloAuto.text.toString()
            val textAnioAuto = editAnioAuto.text.toString()
            val textMatriculaAuto = editMatriculaAuto.text.toString()
            val textFallaAuto = editFallaAuto.text.toString()

            if (textNombreCliente.isEmpty()) {
                editNombreCliente.error = "Ingrese el nombre del cliente"
                editNombreCliente.requestFocus()
                return
            }
            if (textIdCliente.isEmpty()) {
                editIdCliente.error = "Ingrese el ID del cliente"
                editIdCliente.requestFocus()
                return
            }
            if (textTelefonoCliente.isEmpty()) {
                editTelefonoCliente.error = "Ingrese el telefono del cliente"
                editTelefonoCliente.requestFocus()
                return
            }
            if (textCorreoCliente.isEmpty()) {
                editCorreoCliente.error = "Ingrese el correo del cliente"
                editCorreoCliente.requestFocus()
                return
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(textCorreoCliente).matches()) {
                editCorreoCliente.error = "Direccion de correo no valida"
                editCorreoCliente.requestFocus()
                return
            }

            if (textMarcaAuto.isEmpty()) {
                editMarcaAuto.error = "Ingrese la marca del automovil"
                editMarcaAuto.requestFocus()
                return
            }
            if (textModeloAuto.isEmpty()) {
                editModeloAuto.error = "Ingrese la modelo del automovil"
                editModeloAuto.requestFocus()
                return
            }
            if (textAnioAuto.isEmpty()) {
                editAnioAuto.error = "Ingrese la año del automovil"
                editAnioAuto.requestFocus()
                return
            }
            if (textMatriculaAuto.isEmpty()) {
                editMatriculaAuto.error = "Ingrese la matricula del automovil"
                editMatriculaAuto.requestFocus()
                return
            }
            if (textFallaAuto.isEmpty()) {
                editFallaAuto.error = "Ingrese las fallas que presenta el automovil"
                editFallaAuto.requestFocus()
                return
            }

            val order = hashMapOf(
                "nombreCliente" to textNombreCliente,
                "idCliente" to textIdCliente.toInt(),
                "telefonoCliente" to textTelefonoCliente.toInt(),
                "correoCliente" to textCorreoCliente,
                "marcaAuto" to textMarcaAuto,
                "modeloAuto" to textModeloAuto,
                "anioAuto" to textAnioAuto.toInt(),
                "matriculaAuto" to textMatriculaAuto,
                "fechaIngresoAuto" to LocalDate.now().toString(),
                "estadoAuto" to "Pendiente",
                "fallaAuto" to textFallaAuto,
            )

            val nuevoUser = hashMapOf(
                "email" to emailCurrentUsuario
            )

//            db.collection(emailCurrentUsuario.toString()).add(order)

            val userCollectionRef = db.collection("user").document(emailCurrentUsuario.toString())
            val orderCollectionRef = userCollectionRef.collection("order").add(order)

            Toast.makeText(requireContext(), "Orden guardada exitosamente", Toast.LENGTH_SHORT)
                .show()
            limpiarInputs(view)
        }

        //OnclickListener
        btnguardar = binding.guardarBtn
        btnguardar.setOnClickListener(::guardarDatos)

        btnCancelar = binding.cancelarBtn
        btnCancelar.setOnClickListener(::limpiarInputs)

        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
            //Datos cliente
            nombreClienteInput.editText
            idClienteInput.editText
            telefonoClienteInput.editText
            correoClienteInput.editText

            //Datos automovil
            textViewDatosAuto.text
            marcaAutoInput.editText
            modeloAutoInput.editText
            anioAutoInput.editText
            matriculaAutoInput.editText
            fallaAutoInput.editText

            //Botones
            guardarBtn.bottom
            cancelarBtn.bottom
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}