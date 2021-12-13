package com.smart.testing

import com.smart.testing.Checkout.Companion.APPLE
import com.smart.testing.Checkout.Companion.BANANA
import com.smart.testing.Checkout.Companion.PEAR
import org.junit.Assert.assertTrue
import org.junit.Test


/**
 * We need to improve the checkout system of an existing fruit store, it currently supports the most basic things:
- It has a cart where the user can add an item with a price.
- It calculates the total amount the customer has to pay.
- To reduce complexity, assume all numbers are positive and the total will never reach a number you can't process.
 */
class CheckoutTest {


    private val banana = Item(BANANA, 100.0)
    private val pear = Item(PEAR, 10.0)
    private val apple = Item(APPLE, 150.0)

    @Test
    fun `add elements to checkout`() {
        val expected = mutableListOf(banana, apple)
        val checkout = Checkout()

        checkout.add(banana)
        checkout.add(apple)

        assertTrue(expected == checkout.items)
    }

    @Test
    fun `calculate total amount checkout`() {
        val expected = banana.price + apple.price
        val checkout = Checkout()

        checkout.add(banana)
        checkout.add(apple)

        assertTrue(expected == checkout.totalAmount())
    }

    /**
     * ## Iteration 1
    After serious considerations from the research team, we are required now to add the following feature to our program:
    ### Promotions by bulk
    If a customer reaches a certain amount of the same item, the calculation will change according to bulk price rules.
    Example rules with expected cart results:

    | Item   |      Price      |  Bulk Price |
    |----------|:-------------:|------:|
    | Pear |  10 | 2 for 18 |
    | Apple |    20   |   3 for 50 |
    | Banana | 15 |    no bulk price |

    - Cart with 1 pear: $10
    - Cart with 2 pears in a row: $18
    - Cart with 3 pears: $28
    - Cart with 1 pear, 1 apple, 1 pear: $38
    - Cart with 1 pear, 3 apples, 1 pear: $68
    - Cart with 10 bananas: $150
     */

    @Test
    fun `a lot of pears  promo  checkout`() {
        val expected = 18.0 + 18.0 + 10
        val checkout = Checkout()

        checkout.add(pear)
        checkout.add(pear)

        checkout.add(pear)
        checkout.add(pear)

        checkout.add(pear)

        assertTrue(expected == checkout.totalAmount())
    }

    @Test
    fun `two pears in a row promo  checkout`() {
        val expected = 18.0
        val checkout = Checkout()

        checkout.add(pear)
        checkout.add(pear)

        assertTrue(expected == checkout.totalAmount())
    }

    @Test
    fun `one pears in a row promo  checkout`() {
        val expected = 10.0
        val checkout = Checkout()

        checkout.add(pear)

        assertTrue(expected == checkout.totalAmount())
    }
}