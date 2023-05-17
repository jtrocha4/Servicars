package com.example.servicars.adapter

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.servicars.Order
import com.example.servicars.databinding.ItemOrdersBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class OrdersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemOrdersBinding.bind(view)

    fun render(orderModel: Order) {
        binding.vehiculoTxt.text = "${orderModel.marcaAuto} ${orderModel.modeloAuto} ${orderModel.anioAuto} "
        binding.matriculaTxt.text = orderModel.matriculaAuto
        binding.clienteTxt.text = orderModel.nombreCliente
        binding.fechaTxt.text = orderModel.fechaIngresoAuto.toString()
        binding.estadoTxt.text = orderModel.estadoAuto
    }
}