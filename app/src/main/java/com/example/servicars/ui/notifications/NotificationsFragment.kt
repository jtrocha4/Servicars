package com.example.servicars.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.servicars.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        val vehiculoTextView: TextView = binding.vehiculoTextView
        val clienteTextView: TextView = binding.clienteTextView
        val fechaTransTextView: TextView = binding.fechaTextView
        val montoTextView: TextView = binding.montoTextView
        val estadoTextView: TextView = binding.estadoTextView

        //Datos Pruebas
        val ejemploVehiculo: TextView = binding.ejemploVehiculo
        val ejemploCliente: TextView = binding.ejemploCliente
        val ejemploFecha: TextView = binding.ejemploFecha
        val ejemploMonto: TextView = binding.ejemploMonto
        val ejemploEstado: TextView = binding.ejemploEstado


        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
            vehiculoTextView.text
            clienteTextView.text
            fechaTransTextView.text
            montoTextView.text
            estadoTextView.text

            ejemploVehiculo.text
            ejemploCliente.text
            ejemploFecha.text
            ejemploMonto.text
            ejemploEstado.text

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}