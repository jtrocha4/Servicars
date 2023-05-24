package com.example.servicars.components

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.fragment.app.DialogFragment
import com.example.servicars.databinding.DialogEditOrderBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditOrderDialog(

) : DialogFragment() {

    private lateinit var binding: DialogEditOrderBinding
    private val db = FirebaseFirestore.getInstance()

    companion object {
        const val ARG_VEHICULO = "arg_vehiculo"
        const val ARG_MATRICULA = "arg_matricula"
        const val ARG_CLIENTE = "arg_cliente"
        const val ARG_FECHAINGRESO = "arg_fechaIngreso"
        const val ARG_ESTADO = "arg_estado"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding = DialogEditOrderBinding.inflate(LayoutInflater.from(context))
        val currentUsuario = FirebaseAuth.getInstance().currentUser
        val emailCurrentUsuario = currentUsuario?.email

        val dataVehiculo = arguments?.getString(ARG_VEHICULO)
        val dataMatricula = arguments?.getString(ARG_MATRICULA)
        val dataCliente = arguments?.getString(ARG_CLIENTE)
        val dataFechaIngreso = arguments?.getString(ARG_FECHAINGRESO)
        val dataEstado = arguments?.getString(ARG_ESTADO)

        val vehiculoTxt: TextView = binding.vehiculoTxt
        val matriculaTxt: TextView = binding.matriculaTxt
        val clienteTxt: TextView = binding.clienteTxt
        val fechaIngresoTxt: TextView = binding.fechaTxt

        vehiculoTxt.text = dataVehiculo
        matriculaTxt.text = dataMatricula
        clienteTxt.text = dataCliente
        fechaIngresoTxt.text = dataFechaIngreso

        val spinnerEstado = binding.spinnerEstado
        val optionsEstado = arrayOf("Pendiente", "En Proceso", "Completado")
        spinnerEstado.adapter = ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_list_item_1, optionsEstado
        )

        val editBtn = binding.editBtn
        editBtn.setOnClickListener {
            val orderReference =
                db.collection("user").document(emailCurrentUsuario.toString()).collection("order")
            val query = orderReference.whereEqualTo("matriculaAuto", dataMatricula)
            query.get().addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    document.reference.update("estadoAuto", spinnerEstado.selectedItem.toString())
                        .addOnSuccessListener {
                            Toast.makeText(
                                requireContext(), "Orden editada exitosamente", Toast.LENGTH_SHORT
                            ).show()
                        }.addOnFailureListener {
                            Toast.makeText(
                                requireContext(), "Error al editar la orden", Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }
        }

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}