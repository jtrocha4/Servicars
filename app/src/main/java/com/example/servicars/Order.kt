package com.example.servicars

import android.provider.ContactsContract.CommonDataKinds.Email
import java.time.LocalDate
import java.time.LocalDateTime

data class Order(
    val nombreCliente: String? = null,
    val idCliente: Int? = null,
    val telefonoCliente: Int? = null,
    val correoCliente: String? = null,
    val marcaAuto: String? = null,
    val modeloAuto: String? = null,
    val anioAuto: Int? = null,
    val matriculaAuto: String? = null,
    val fechaIngresoAuto: String? = null,
    val estadoAuto: String = "Pendiente",
    val fallaAuto: String? = null
)