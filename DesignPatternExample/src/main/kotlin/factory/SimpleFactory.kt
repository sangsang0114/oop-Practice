package factory

class VehicleFactory {
    fun getVehicle(vehicleType: String): Vehicle {
        return when (vehicleType) {
            "car" -> Car()
            "truck" -> Truck()
            else -> throw IllegalArgumentException("Unknown vehicle type")
        }
    }
}

fun simpleFactoryMain() {
    val factory = VehicleFactory()
    val car = factory.getVehicle("car")
    car.drive()

    val truck = factory.getVehicle("truck")
    truck.drive()
}