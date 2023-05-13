package com.example.servicars

import java.time.LocalDate

class OrdersProvider {
    companion object{
        val OrderList = mutableListOf<Order>(
            Order("Kia Sportage 2016", "UEY699", "Jaime Trocha", LocalDate.now(), "Terminado"),
            Order("Chevrolet Tahoe 2020", "EEY489", "Luis Quintero", LocalDate.now(), "En taller")
        )
        fun addOrder(order: Order) {
            OrderList.add(order)
        }
    }
}