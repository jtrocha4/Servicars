package com.example.servicars

import android.provider.ContactsContract.CommonDataKinds.Email
import java.time.LocalDate

data class Order(
    val cliente: String,
    val idCliente: Int,
    val telefonoCliente: Int,
    val correoCliente: String,
    val marcaAuto: String,
    val modeloAuto: String,
    val anioAuto: Int,
    val matriculaAuto: String,
    val fechaIngresoAuto: LocalDate = LocalDate.now(),
    val estadoAuto: String = "Pendiente",
    val fallaAuto: String
)