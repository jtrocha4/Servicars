package com.example.servicars.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.servicars.Order
import com.example.servicars.R
import com.example.servicars.databinding.ActivityDetailBinding
import java.time.LocalDate

class OrdersApadter(
    private val orderList: ArrayList<Order>, private val onClickListener: (Order) -> Unit
) : RecyclerView.Adapter<OrdersApadter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersApadter.MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_orders, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrdersApadter.MyViewHolder, position: Int) {
        val order: Order = orderList[position]
        holder.auto.text = "${order.marcaAuto} ${order.modeloAuto} ${order.anioAuto}"
        holder.matricula.text = order.matriculaAuto
        holder.cliente.text = order.nombreCliente
        holder.fechaIngreso.text = order.fechaIngresoAuto
        holder.estado.text = "Pendiente"

        holder.itemView.setOnClickListener {
            onClickListener.invoke(order)
        }

    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val auto: TextView = itemView.findViewById(R.id.vehiculoTxt)
        val matricula: TextView = itemView.findViewById(R.id.matriculaTxt)
        val cliente: TextView = itemView.findViewById(R.id.clienteTxt)
        val fechaIngreso: TextView = itemView.findViewById(R.id.fechaTxt)
        val estado: TextView = itemView.findViewById(R.id.estadoTxt)
    }

}