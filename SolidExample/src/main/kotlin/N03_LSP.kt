package org.yoon

open class Bird {
    //LSP 위반
    open fun fly() {
        println("Bird fly")
    }

    //LSP 준수
    open fun move() {
        println("Bird move")
    }
}

class Sparrow : Bird() {
    override fun fly() {
        println("Sparrow fly")
    }
}

class Ostrich : Bird() {

    //LSP 위반
    override fun fly() {
        throw UnsupportedOperationException("Ostrich can't fly")
    }

    override fun move() {
        print("Ostrich move")
    }
}

fun makeBirdFly(bird: Bird) {
    bird.fly()
}

fun makeBirdMove(bird: Bird) {
    bird.move()
}

/*Circle Ellipse Problem*/
open class Ellipse {
    var radiusX: Double = 0.0
    var radiusY: Double = 0.0

    fun setRadius(x: Double, y: Double) {
        radiusX = x
        radiusY = y
    }
}

class Circle : Ellipse() {
    fun setRadius(radius: Double) {
        radiusX = radius
        radiusY = radius
    }
}

fun main() {
    val ellipse: Ellipse = Circle()
    ellipse.setRadius(5.0, 10.0) // 원의 정의를 깨트림
}