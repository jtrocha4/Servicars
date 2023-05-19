package com.example.servicars.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.servicars.Order
import com.example.servicars.databinding.ItemOrdersBinding

class OrdersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemOrdersBinding.bind(view)

    fun render(
        orderModel: Order,
        onClickListener: (Order) -> Unit,
        onClickDelete: (Order) -> Unit,
        onClickEdit: () -> Unit
    ) {
        binding.vehiculoTxt.text =
            "${orderModel.marcaAuto} ${orderModel.modeloAuto} ${orderModel.anioAuto} "
        binding.matriculaTxt.text = orderModel.matriculaAuto
        binding.clienteTxt.text = orderModel.nombreCliente
        binding.fechaTxt.text = orderModel.fechaIngresoAuto.toString()
        binding.estadoTxt.text = orderModel.estadoAuto

        binding.deleteBtn.setOnClickListener {
            onClickDelete(orderModel)
        }

        binding.editBtn.setOnClickListener {
            onClickEdit()
        }

        itemView.setOnClickListener {
            onClickListener.invoke(orderModel)
        }
    }
}