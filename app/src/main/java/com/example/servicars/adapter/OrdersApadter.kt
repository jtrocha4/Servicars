package com.example.servicars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.servicars.Order
import com.example.servicars.R

class OrdersApadter(
    private val orderList: ArrayList<Order>,
    private val onClickListener: (Order) -> Unit,
    private val onClickDelete: (Order) -> Unit,
    private val onClickEdit: (Order) -> Unit
) : RecyclerView.Adapter<OrdersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OrdersViewHolder(layoutInflater.inflate(R.layout.item_orders, parent, false))
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val item = orderList[position]
        holder.render(item, onClickListener, onClickDelete, onClickEdit)
    }


}