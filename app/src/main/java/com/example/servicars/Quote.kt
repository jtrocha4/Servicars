package com.example.servicars

import com.google.type.Money

data class Quote(
    val marcaAuto: String? = null,
    val modeloAuto: String? = null,
    val anioAuto: Int? = null,
    val matriculaAuto: String? = null,
    val fechaQuote: String? = null,
    val montoQuote: Int? = null,
    val estadoQuote: String? = null,
    val descripcionQuote: String? = null
)