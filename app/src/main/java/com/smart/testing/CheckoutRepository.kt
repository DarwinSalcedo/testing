package com.smart.testing

interface CheckoutRepository {
    fun add(item: Item)
    fun totalAmount(): Double
}
