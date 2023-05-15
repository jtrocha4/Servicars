package com.example.servicars.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.servicars.Order
import com.example.servicars.databinding.ItemOrdersBinding

class OrdersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemOrdersBinding.bind(view)

    fun render(orderModel: Order, onClickListener: (Order) -> Unit) {
        binding.vehiculoTxt.text = "${orderModel.marcaAuto} ${orderModel.modeloAuto} ${orderModel.anioAuto} "
        binding.matriculaTxt.text = orderModel.matriculaAuto
        binding.clienteTxt.text = orderModel.cliente
        binding.fechaTxt.text = orderModel.fechaIngresoAuto.toString()
        binding.estadoTxt.text = orderModel.estadoAuto

        itemView.setOnClickListener {
            onClickListener(orderModel)
        }

    }
}