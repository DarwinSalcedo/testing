package com.smart.testing

class Checkout(val items: MutableList<Item> = mutableListOf()) : CheckoutRepository {
    companion object {
        const val PEAR = "Pear"
        const val BANANA = "Banana"
        const val APPLE = "Apple"
        const val PROMO_PEARS = 2.0
    }


    override fun add(item: Item) {
        items.add(item)
    }

    override fun totalAmount(): Double {

        val promoPears = this.items.count { it.name == PEAR }.div(2)
        return this.items.sumOf { it.price }.minus(promoPears * PROMO_PEARS)

    }


}