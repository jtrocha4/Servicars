package com.example.servicars.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicars.DetailActivity
import com.example.servicars.Order
import com.example.servicars.OrdersProvider
import com.example.servicars.adapter.OrdersApadter
import com.example.servicars.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

    ): View {

        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val currentUsuario = FirebaseAuth.getInstance().currentUser
        val emailCurrentUsuario = currentUsuario?.email

        val data = db.collection(emailCurrentUsuario.toString()).get()
        data.addOnSuccessListener { result ->
            for (document in result) {
                Log.d("Lectura FireStores", "${document.id} => ${document.data}")
            }
        }

        val textView: TextView = binding.textHome

        fun onItemSelected(order: Order) {
            val intent = Intent(requireContext(), DetailActivity::class.java)

//            intent.putExtra("cliente", order.cliente)
//            intent.putExtra("idCliente", order.idCliente)
//            intent.putExtra("telefonoCliente", order.telefonoCliente)
//            intent.putExtra("correoCliente", order.correoCliente)
//            intent.putExtra("marcaAuto", order.marcaAuto)
//            intent.putExtra("modeloAuto", order.modeloAuto)
//            intent.putExtra("anioAuto", order.anioAuto)
//            intent.putExtra("matriculaAuto", order.matriculaAuto)
//            intent.putExtra("fechaIngresoAuto", order.fechaIngresoAuto.toString())
//            intent.putExtra("estadoAuto", order.estadoAuto)
//            intent.putExtra("fallaAuto", order.fallaAuto)

            startActivity(intent)
        }

        fun initRecyclerView() {
            binding.recyclerOrders.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerOrders.adapter = OrdersApadter(
                OrdersProvider.OrderList
            ) { order -> onItemSelected(order) }
        }

        initRecyclerView()

        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}