package com.example.servicars.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.servicars.Quote
import com.example.servicars.databinding.ItemQuoteBinding

class QuoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemQuoteBinding.bind(view)

    fun render(quoteModel: Quote, onclickListener: (Quote) -> Unit) {
        binding.vehiculoTxt.text = "${quoteModel.marcaAuto} ${quoteModel.modeloAuto} ${quoteModel.anioAuto}"
        binding.matriculaTxt.text = "${quoteModel.matriculaAuto}"
        binding.fechaTxt.text = "${quoteModel.fechaQuote}"
        binding.estadoQuoteTxt.text = "${quoteModel.estadoQuote}"
        binding.montoQuoteTxt.text = "${quoteModel.montoQuote}"
        binding.descripcionQuoteTxt.text = "${quoteModel.descripcionQuote}"
    }

}