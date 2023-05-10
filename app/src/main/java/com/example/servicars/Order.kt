package com.example.servicars

import java.util.Date

data class Order(
    val vehiculo: String,
    val matricula: String,
    val cliente: String,
    val fechaIngreso: String,
    val estado: String
)