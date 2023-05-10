package com.example.servicars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.servicars.Order
import com.example.servicars.R

class OrdersApadter(private val oderList:List<Order>) : RecyclerView.Adapter<OrdersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OrdersViewHolder(layoutInflater.inflate(R.layout.item_orders, parent, false))
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val item = oderList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return oderList.size
    }


}