package com.example.servicars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.servicars.Quote
import com.example.servicars.R

class QuoteAdapter(
    private val quoteList: ArrayList<Quote>,
    private val onClickDelete: (Quote) -> Unit
) :
    RecyclerView.Adapter<QuoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return QuoteViewHolder(layoutInflater.inflate(R.layout.item_quote, parent, false))
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = quoteList[position]
        holder.render(item, onClickDelete)
    }

}