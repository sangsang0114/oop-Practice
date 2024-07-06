package org.yoon

import org.yoon.discountPolicy.ExpirationDiscountPolicy
import org.yoon.discountPolicy.NightTimeDiscountPolicy
import org.yoon.discountPolicy.NoDiscountPolicy
import java.time.LocalDate
import java.time.LocalDateTime

fun main() {
    val customer = Customer("yoon")
    val martOwner = MartOwner("이마트")
    val cola = Product(
        "콜라",
        1000,
        LocalDate.now().plusDays(1),
        listOf(ExpirationDiscountPolicy(), NightTimeDiscountPolicy(), NoDiscountPolicy())
    )
    val snack = Product(
        "새우 과자",
        1000,
        LocalDate.now().plusDays(1),
        listOf(ExpirationDiscountPolicy(), NightTimeDiscountPolicy(), NoDiscountPolicy())
    )
    val egg = Product(
        "계란",
        500,
        LocalDate.now().plusDays(10),
        listOf(ExpirationDiscountPolicy(), NightTimeDiscountPolicy(), NoDiscountPolicy())
    )

    customer.addToCart(cola, 10)
    customer.addToCart(egg, 30)

    val payment = customer.requestCheckout(martOwner)

    customer.pay(martOwner, payment)
}

class Customer(private val name: String) {
    private val cart = Cart()
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

class Cart {
    private val cartItemList = mutableListOf<CartItem>()
    fun add(cartItem: CartItem) {
        cartItemList.add(cartItem)
    }

    fun getTotalPrice(): Int {
        return cartItemList.map { it.getTotalPrice() }
            .reduce { acc, price -> acc + price }
    }
}

class Card {
    fun checkout(payment: Payment) {
        payment.paid()
    }

}

data class MartOwner(val name: String) {
    private val paymentBox = mutableListOf<Payment>()
    fun createPayment(customerId: String, cart: Cart): Payment {
        val totalPrice: Int = cart.getTotalPrice()
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
        return product.calculatePrice(quantity)
    }
}

interface DiscountPolicy {
    val discountPercentage: Double
    fun isSatisfiedBy(product: Product): Boolean
    fun calculateDiscountAmount(originalPrice: Int, product: Product): Int {
        return if (isSatisfiedBy(product)) {
            originalPrice.times(discountPercentage).toInt()
        } else {
            0
        }
    }
}

data class Product(
    private val name: String,
    val price: Int,
    private val expireDate: LocalDate,
    private val discountPolicyList: List<DiscountPolicy>
) {
    fun calculatePrice(quantity: Int): Int {
        val bestDiscountPolicy = getDiscountPolicy()
        val originalPrice = calculateOriginalPrice(quantity)
        val discountPrice = bestDiscountPolicy.calculateDiscountAmount(originalPrice, this)
        return originalPrice - discountPrice
    }

    private fun calculateOriginalPrice(quantity: Int): Int {
        return price * quantity
    }


    private fun getDiscountPolicy(): DiscountPolicy {
        return discountPolicyList
            .filter { it.isSatisfiedBy(this) }
            .maxByOrNull { it.discountPercentage } ?: NoDiscountPolicy()
    }

    fun isExpiredSoon(now: LocalDateTime): Boolean {
        return expireDate.minusDays(1).equals(now)
    }
}

class Payment(
    private val customerId: String,
    private val purchaseInfo: Cart,
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