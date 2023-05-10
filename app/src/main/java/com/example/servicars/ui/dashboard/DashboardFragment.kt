package com.example.servicars.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.servicars.R
import com.example.servicars.SecondActivity
import com.example.servicars.databinding.FragmentDashboardBinding
import com.google.android.material.textfield.TextInputLayout

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var btnguardar: Button
    private lateinit var btnCancelar: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

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
        fun limpiarInputs(view: View){
            val editNombreCliente = root.findViewById<EditText>(R.id.nombreClienteEditText)
            val editIdCliente = root.findViewById<EditText>(R.id.idClienteEditText)
            val editTelefonoCliente = root.findViewById<EditText>(R.id.telefonoClienteEditText)
            val editCorreoCliente = root.findViewById<EditText>(R.id.correoClienteEditText)

            val editMarcaAuto = root.findViewById<EditText>(R.id.marcaAutoEditText)
            val editModeloAuto = root.findViewById<EditText>(R.id.modeloAutoEditText)
            val editAnioAuto = root.findViewById<EditText>(R.id.anioAutoEditText)
            val editMatriculaAuto = root.findViewById<EditText>(R.id.matriculaAutoEditText)
            val editFallaAuto = root.findViewById<EditText>(R.id.fallaAutoEditText)

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
        fun guardarDatos (view: View){
            val editNombreCliente = root.findViewById<EditText>(R.id.nombreClienteEditText)
            val editIdCliente = root.findViewById<EditText>(R.id.idClienteEditText)
            val editTelefonoCliente = root.findViewById<EditText>(R.id.telefonoClienteEditText)
            val editCorreoCliente = root.findViewById<EditText>(R.id.correoClienteEditText)

            val editMarcaAuto = root.findViewById<EditText>(R.id.marcaAutoEditText)
            val editModeloAuto = root.findViewById<EditText>(R.id.modeloAutoEditText)
            val editAnioAuto = root.findViewById<EditText>(R.id.anioAutoEditText)
            val editMatriculaAuto = root.findViewById<EditText>(R.id.matriculaAutoEditText)
            val editFallaAuto = root.findViewById<EditText>(R.id.fallaAutoEditText)

            val textNombreCliente = editNombreCliente.text.toString()
            val textIdCliente = editIdCliente.text.toString()
            val textTelefonoCliente = editTelefonoCliente.text.toString()
            val textCorreoCliente = editCorreoCliente.text.toString()

            val textMarcaAuto = editMarcaAuto.text.toString()
            val textModeloAuto = editModeloAuto.text.toString()
            val textAnioAuto = editAnioAuto.text.toString()
            val textMatriculaAuto = editMatriculaAuto.text.toString()
            val textFallaAuto = editFallaAuto.text.toString()

            if(textNombreCliente.isEmpty()){
                editNombreCliente.error = "Ingrese el nombre del cliente"
                editNombreCliente.requestFocus()
                return
            }
            if(textIdCliente.isEmpty()){
                editIdCliente.error = "Ingrese el ID del cliente"
                editIdCliente.requestFocus()
                return
            }
            if(textTelefonoCliente.isEmpty()){
                editTelefonoCliente.error = "Ingrese el telefono del cliente"
                editTelefonoCliente.requestFocus()
                return
            }
            if(textCorreoCliente.isEmpty()){
                editCorreoCliente.error = "Ingrese el correo del cliente"
                editCorreoCliente.requestFocus()
                return
            }

            if(textMarcaAuto.isEmpty()){
                editMarcaAuto.error = "Ingrese la marca del automovil"
                editMarcaAuto.requestFocus()
                return
            }
            if(textModeloAuto.isEmpty()){
                editModeloAuto.error = "Ingrese la modelo del automovil"
                editModeloAuto.requestFocus()
                return
            }
            if(textAnioAuto.isEmpty()){
                editAnioAuto.error = "Ingrese la año del automovil"
                editAnioAuto.requestFocus()
                return
            }
            if(textMatriculaAuto.isEmpty()){
                editMatriculaAuto.error = "Ingrese la matricula del automovil"
                editMatriculaAuto.requestFocus()
                return
            }
            if (textFallaAuto.isEmpty()){
                editFallaAuto.error = "Ingrese las fallas que presenta el automovil"
                editFallaAuto.requestFocus()
                return
            }

            Toast.makeText(requireContext(), "Datos guardados exitosamente", Toast.LENGTH_SHORT).show()
            limpiarInputs(view)
        }

        //OnclickListener
        btnguardar = root.findViewById(R.id.guardarBtn)
        btnguardar.setOnClickListener(::guardarDatos)

        btnCancelar = root.findViewById(R.id.cancelarBtn)
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