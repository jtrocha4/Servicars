package com.example.servicars.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.servicars.Order
import com.example.servicars.databinding.ItemOrdersBinding

class OrdersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemOrdersBinding.bind(view)

    fun render(orderModel: Order) {
        binding.vehiculoTxt.text = orderModel.vehiculo
        binding.matriculaTxt.text = orderModel.matricula
        binding.clienteTxt.text = orderModel.cliente
        binding.fechaTxt.text = orderModel.fechaIngreso
        binding.estadoTxt.text = orderModel.estado
    }
}