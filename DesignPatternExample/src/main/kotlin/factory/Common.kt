package factory

interface Vehicle {
    fun drive()
}

class Car : Vehicle {
    override fun drive() {
        println("Driving a Car")
    }
}

class Truck : Vehicle {
    override fun drive() {
        println("Driving a Truck")
    }
}