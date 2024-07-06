package org.yoon.discountPolicy

import org.yoon.DiscountPolicy
import org.yoon.Product

class NoDiscountPolicy : DiscountPolicy {
    override val discountPercentage: Double = 0.0

    override fun isSatisfiedBy(product: Product): Boolean {
        return false
    }
}