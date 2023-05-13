package com.example.servicars.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.servicars.Order
import com.example.servicars.databinding.ItemOrdersBinding

class OrdersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemOrdersBinding.bind(view)

    fun render(orderModel: Order, onClickListener: (Order) -> Unit) {
        binding.vehiculoTxt.text = orderModel.vehiculo
        binding.matriculaTxt.text = orderModel.matricula
        binding.clienteTxt.text = orderModel.cliente
        binding.fechaTxt.text = orderModel.fechaIngreso.toString()
        binding.estadoTxt.text = orderModel.estado

        itemView.setOnClickListener {
            onClickListener(orderModel)
        }

    }
}