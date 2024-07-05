package org.yoon

import java.time.LocalDate
import java.time.LocalDateTime

fun main() {
    val customer = Customer("yoon")
    val martOwner = MartOwner("이마트")
    val cola = Product("콜라", 1000, LocalDate.now().plusDays(1))
    val snack = Product("새우 과자", 1000, LocalDate.now().plusDays(1))
    val egg = Product("계란", 500, LocalDate.now().plusDays(10))

    customer.addToCart(cola, 10)
    customer.addToCart(egg, 30)

    val payment = customer.requestCheckout(martOwner)

    customer.pay(martOwner, payment)
}

class Customer(private val name: String) {
    private val cart = mutableListOf<Pair<Product, Int>>()
    private val customerId = "${name}-${LocalDateTime.now()}"
    private val card = Card()
    fun addToCart(product: Product, quantity: Int) {
        // cart.add(Pair(product, quantity))
        cart.add(product to quantity)
    }

    fun requestCheckout(martOwner: MartOwner): Payment {
        return martOwner.createPayment(customerId, cart)
    }

    fun pay(martOwner: MartOwner, payment: Payment) {
        martOwner.processPayment(card, payment)
    }
}

class Card {
    fun checkout(payment: Payment) {
        payment.paid()
    }

}

data class MartOwner(val name: String) {
    fun createPayment(customerId: String, cart: List<Pair<Product, Int>>): Payment {
        val totalPrice: Int = cart
            .map { (product, quantity) -> product.price * quantity }
            .reduce { acc, price -> acc + price }
        return Payment(customerId, cart, totalPrice)
    }

    fun processPayment(card: Card, payment: Payment) {
        card.checkout(payment)
        TODO("???")
    }
}

data class Product(
    private val name: String,
    val price: Int,
    private val expireDate: LocalDate
)

class Payment(
    private val customerId: String,
    private val purchaseInfo: List<Pair<Product, Int>>,
    private val totalPrice: Int,
    private var paidAt: LocalDateTime? = null
) {
    fun paid() {
        this.paidAt = LocalDateTime.now()
    }

    private val paymentId: String = "payment-$customerId-${LocalDateTime.now()}"
}