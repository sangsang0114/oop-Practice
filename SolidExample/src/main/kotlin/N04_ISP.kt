package org.yoon

interface Worker {
    fun work()
    fun eat()
}

class Developer: Worker {
    override fun work() {
        println("Developer work")
    }
    
    override fun eat() {
        println("Eat")
    }
}

class Robot: Worker{
    override fun work() {
        println("Robot work")
    }
    
    override fun eat() {
        //로봇은 먹지 않음. 불필요한 구현
    }
}