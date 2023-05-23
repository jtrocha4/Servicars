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
import com.example.servicars.R
import com.example.servicars.adapter.OrdersApadter
import com.example.servicars.components.EditOrderDialog
import com.example.servicars.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    lateinit var OrderArrayList: ArrayList<Order>
    lateinit var Adapter: OrdersApadter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

    ): View {

        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val currentUsuario = FirebaseAuth.getInstance().currentUser
        val emailCurrentUsuario = currentUsuario?.email

        OrderArrayList = arrayListOf()

        val textView: TextView = binding.textHome

        fun onItemSelected(order: Order) {
            val intent = Intent(requireContext(), DetailActivity::class.java)

            intent.putExtra("nombreCliente", order.nombreCliente)
            intent.putExtra("idCliente", order.idCliente)
            intent.putExtra("telefonoCliente", order.telefonoCliente)
            intent.putExtra("correoCliente", order.correoCliente)
            intent.putExtra("marcaAuto", order.marcaAuto)
            intent.putExtra("modeloAuto", order.modeloAuto)
            intent.putExtra("anioAuto", order.anioAuto)
            intent.putExtra("matriculaAuto", order.matriculaAuto)
            intent.putExtra("fechaIngresoAuto", order.fechaIngresoAuto.toString())
            intent.putExtra("estadoAuto", order.estadoAuto)
            intent.putExtra("fallaAuto", order.fallaAuto)

            startActivity(intent)
        }

        fun onItemEdit(order: Order) {
            val dialogFragment = EditOrderDialog()
            val args = Bundle().apply {
                putString(
                    EditOrderDialog.ARG_VEHICULO,
                    "${order.marcaAuto} ${order.modeloAuto} ${order.anioAuto}"
                )
                putString(EditOrderDialog.ARG_MATRICULA, "${order.matriculaAuto}")
                putString(EditOrderDialog.ARG_CLIENTE, "${order.nombreCliente}")
                putString(EditOrderDialog.ARG_FECHAINGRESO, "${order.fechaIngresoAuto}")
                putString(EditOrderDialog.ARG_ESTADO, "${order.estadoAuto}")
            }
            dialogFragment.arguments = args
            dialogFragment.show(parentFragmentManager, "EditOrderDialog")
        }


        fun onItemDelete(order: Order) {
            val orderReference = db.collection(emailCurrentUsuario.toString())
            val query = orderReference.whereEqualTo("matriculaAuto", order.matriculaAuto)
            query.get().addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    document.reference.delete().addOnSuccessListener {
                        Toast.makeText(
                            requireContext(), "Orden eliminada exitosamente", Toast.LENGTH_SHORT
                        ).show()
                    }.addOnFailureListener {
                        Toast.makeText(
                            requireContext(), "Error al eliminar la orden", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        //Lectura Firestore
        fun EventChangeListener() {
            db.collection(emailCurrentUsuario.toString())
                .addSnapshotListener(object : EventListener<QuerySnapshot> {
                    override fun onEvent(
                        value: QuerySnapshot?, error: FirebaseFirestoreException?
                    ) {
                        if (error != null) {
                            Log.e("Lectura FireStore", error.message.toString())
                            return
                        }
                        for (doc: DocumentChange in value?.documentChanges!!) {
                            if (doc.type == DocumentChange.Type.ADDED) {
                                OrderArrayList.add(doc.document.toObject(Order::class.java))
                            }
                        }
                        Adapter.notifyDataSetChanged()
                    }
                })
        }

        fun initRecyclerView() {
            binding.recyclerOrders.layoutManager = LinearLayoutManager(requireContext())
            OrderArrayList = arrayListOf()
            Adapter = OrdersApadter(OrderArrayList,
                onClickListener = { order -> onItemSelected(order) },
                onClickDelete = { order -> onItemDelete(order) },
                onClickEdit = { order -> onItemEdit(order) })
            binding.recyclerOrders.adapter = Adapter
        }

        initRecyclerView()
        EventChangeListener()

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