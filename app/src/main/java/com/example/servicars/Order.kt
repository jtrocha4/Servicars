package com.example.servicars

import java.time.LocalDate
import java.util.Date

data class Order(
    val vehiculo: String,
    val matricula: String,
    val cliente: String,
    val fechaIngreso: LocalDate = LocalDate.now(),
    val estado: String = "Pendiente"
)