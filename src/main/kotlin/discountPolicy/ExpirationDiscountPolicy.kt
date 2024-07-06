package org.yoon.discountPolicy

import org.yoon.DiscountPolicy
import org.yoon.Product
import java.time.LocalDateTime

class ExpirationDiscountPolicy : DiscountPolicy {
    override val discountPercentage: Double = 0.7
    override fun isSatisfiedBy(product: Product): Boolean {
        return product.isExpiredSoon(LocalDateTime.now())
    }
}