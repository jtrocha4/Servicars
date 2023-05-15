package com.example.servicars

import java.time.LocalDate

class OrdersProvider {
    companion object {
        val OrderList = mutableListOf<Order>(
            Order(
                "Jaime Trocha",
                1002,
                3117,
                "jtrocha@email.com",
                "Kia",
                "Sportage",
                2016,
                "UEE-689",
                LocalDate.now(),
                "Pendiente",
                "Ruido en la suspension delantera"
            )
        )

        fun addOrder(order: Order) {
            OrderList.add(order)
        }
    }
}