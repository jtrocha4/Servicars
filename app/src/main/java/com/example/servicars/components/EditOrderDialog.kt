package com.example.servicars.components

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.servicars.databinding.DialogEditOrderBinding

class EditOrderDialog(

) : DialogFragment() {

    private lateinit var binding: DialogEditOrderBinding

    companion object {
        const val ARG_VEHICULO = "arg_vehiculo"
        const val ARG_MATRICULA = "arg_matricula"
        const val ARG_CLIENTE = "arg_cliente"
        const val ARG_FECHAINGRESO = "arg_fechaIngreso"
        const val ARG_ESTADO = "arg_estado"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding = DialogEditOrderBinding.inflate(LayoutInflater.from(context))
        val dataVehiculo = arguments?.getString(ARG_VEHICULO)
        val dataMatricula = arguments?.getString(ARG_MATRICULA)
        val dataCliente = arguments?.getString(ARG_CLIENTE)
        val dataFechaIngreso = arguments?.getString(ARG_FECHAINGRESO)
        val dataEstado = arguments?.getString(ARG_ESTADO)

        val vehiculoTxt: TextView = binding.vehiculoTxt
        val matriculaTxt: TextView = binding.matriculaTxt
        val clienteTxt: TextView = binding.clienteTxt
        val fechaIngresoTxt: TextView = binding.fechaTxt
        val estadoTxt: TextView = binding.estadoTextView

        vehiculoTxt.text = dataVehiculo
        matriculaTxt.text = dataMatricula
        clienteTxt.text = dataCliente
        fechaIngresoTxt.text = dataFechaIngreso
        estadoTxt.text = dataEstado

        val spinnerEstado = binding.spinnerEstado
        val optionsEstado = arrayOf("Pendiente", "En Proceso", "Completado")
        spinnerEstado.adapter = ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_list_item_1, optionsEstado
        )

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}