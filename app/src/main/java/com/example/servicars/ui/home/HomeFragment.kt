package com.example.servicars.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.servicars.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        //val autoCard: CardView = binding.vehiculoCardView
        //val vehiculorow: TableRow = binding.VehiculoRow

        val vehiculoTextView: TextView = binding.vehiculoTextView
        val matriculaTextView: TextView = binding.matriculaTextView
        val clienteTextView: TextView = binding.clienteTextView
        val fechaIngresoTextView: TextView = binding.fechaTextView
        val estadoTextView: TextView = binding.estadoTextView

        //Datos de prueba
        val ejemploVehiculo: TextView = binding.ejemploVehiculo
        val ejemploMatricula: TextView = binding.ejemploMatricula
        val ejemploCliente: TextView = binding.ejemploCliente
        val ejemploFecha: TextView = binding.ejemploFecha
        val ejemploEstado: TextView = binding.ejemploEstado


        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
            vehiculoTextView.text
            matriculaTextView.text
            clienteTextView.text
            fechaIngresoTextView.text
            estadoTextView.text

            ejemploVehiculo.text
            ejemploMatricula.text
            ejemploCliente.text
            ejemploFecha.text
            ejemploEstado.text
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}