package org.yoon.discountPolicy

import org.yoon.DiscountPolicy
import org.yoon.Product
import java.time.LocalDateTime
import java.time.LocalTime

class NightTimeDiscountPolicy : DiscountPolicy {
    override val discountPercentage: Double = 0.3
    override fun isSatisfiedBy(product: Product): Boolean {
        val now = LocalDateTime.now()
        return now.isAfter(LocalDateTime.of(now.toLocalDate(), LocalTime.of(2, 0))) &&
                now.isBefore(LocalDateTime.of(now.toLocalDate(), LocalTime.of(5, 0)))
    }
}