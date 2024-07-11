package org.yoon

class Customer(var name: String, var age: Int)

//문제 상황
class CustomerManager {
    private val customers: MutableList<Customer> = mutableListOf()

    fun addCustomer(name: String, age: Int) {
        customers.add(Customer(name, age))
    }

    fun removeCustomer(name: String) {
        customers.removeIf { x -> x.name == name }
    }

    fun updateCustomer(name: String, age: Int) {
        val customer = customers.find { x -> x.name == name }
        if (customer != null) {
            customer.age = age
        }
    }

    fun printCustomers() {
        for (customer in customers) {
            print("Name: ${customer.name}, Age: ${customer.age}")
        }
    }
}

//개선된 예시
class CustomerRepository {
    private val customers: MutableList<Customer> = mutableListOf()

    fun addCustomer(customer: Customer) {
        customers.add(customer)
    }

    fun removeCustomer(name: String) {
        customers.removeIf { x -> x.name == name }
    }

    fun findCustomer(name: String): Customer? {
        return customers.find { x -> x.name == name }
    }

    fun getAllCustomers(): List<Customer> {
        return customers
    }
}

class CustomerService(private val customerRepository: CustomerRepository) {

    fun addCustomer(name: String, age: Int) {
        val customer = Customer(name, age)
        customerRepository.addCustomer(customer)
    }

    fun removeCustomer(name: String) {
        customerRepository.removeCustomer(name)
    }

    fun updateCustomer(name: String, age: Int) {
        val customer = customerRepository.findCustomer(name)
        customer?.age = age
    }

    fun printCustomers() {
        val customers = customerRepository.getAllCustomers()
        for (customer in customers) {
            print("Name: ${customer.name}, Age: ${customer.age}")
        }
    }
}