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
    private val cart = mutableListOf<CartItem>()
    private val customerId = "${name}-${LocalDateTime.now()}"
    private val card = Card()
    fun addToCart(product: Product, quantity: Int) {
        // cart.add(Pair(product, quantity))
        cart.add(CartItem(product, quantity))
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
    private val paymentBox = mutableListOf<Payment>()
    fun createPayment(customerId: String, cart: List<CartItem>): Payment {
        val totalPrice: Int = cart
            .map { it.getTotalPrice() }
            .reduce { acc, price -> acc + price }
        val payment = Payment(customerId, cart, totalPrice)
        paymentBox.add(payment)

        return payment
    }

    fun processPayment(card: Card, payment: Payment) {
        card.checkout(payment)
        TODO("???")
    }
}

class CartItem(
    private val product: Product,
    private val quantity: Int
) {
    fun getTotalPrice(): Int {
        return product.price * quantity
    }
}

data class Product(
    private val name: String,
    val price: Int,
    private val expireDate: LocalDate
)

class Payment(
    private val customerId: String,
    private val purchaseInfo: List<CartItem>,
    private val totalPrice: Int,
    private var paidAt: LocalDateTime? = null
) {
    fun paid() {
        this.paidAt = LocalDateTime.now()
    }

    fun printPayment(): String {
        return """
            --- 구매내역 ---
            TODO("구매내역")
            --- 총 가격 ---
            $totalPrice
        """.trimIndent()
    }

    private val paymentId: String = "payment-$customerId-${LocalDateTime.now()}"
}