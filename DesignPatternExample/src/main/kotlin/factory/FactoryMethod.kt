package factory

abstract class AbstractVehicleFactory {
    abstract fun getVehicle(): Vehicle
}

class TruckFactory : AbstractVehicleFactory() {
    override fun getVehicle(): Vehicle {
        return Truck()
    }
}

class CarFactory : AbstractVehicleFactory() {
    override fun getVehicle(): Vehicle {
        return Car()
    }
}

fun FactoryMethodMain() {
    val truck = TruckFactory().getVehicle()
    val car = CarFactory().getVehicle()
}