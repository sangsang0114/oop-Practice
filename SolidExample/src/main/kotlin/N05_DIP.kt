package org.yoon

/**** 올바른 예시 ***/
interface Keyboard {
    fun connect()
}

interface Monitor {
    fun turnOn()
}

class StandardKeyboard : Keyboard {
    override fun connect() = println("키보드 연결됨")
}

class LedMonitor : Monitor {
    override fun turnOn() = println("모니터 켜짐")
}

class Computer(private val keyboard: Keyboard, private val monitor: Monitor) {
    fun start() {
        keyboard.connect()
        monitor.turnOn()
        println("컴퓨터가 시작되었습니다")
    }
}

/**** 고수준 모듈이 저수준 모듈에 직접 의존하게 되면 생기는 일***/

class HardDrive {
    fun readData(): String = "데이터 읽기"
}

//유연성 부족으로 인해 내부 구현을 변경해야 한다.
class SSD {
    fun readData(): String = "SSD에서 데이터 읽기"

    //메서드 시그니처가 변경 되는 경우(인터페이스가 변경 되는 경우) Computer에도 영향
//    fun readData(lba: Long, size: Int): String = "특정 영역에서 데이터 읽기"
}

//새로운 유형을 추가하 때 computer 클래스가 계속 커지고 복잡해진다.
class CloudStorage {
    fun readData(): String = "클라우드에서 데이터 읽기"
}

class Computer2 {
    private val hardDrive = HardDrive()
    private val ssd = SSD()
    private val cloudStorage = CloudStorage()
    fun start() {
        val data = hardDrive.readData()
        //val data = ssd.readData()
//        val data2 = hardDrive.readData(512, 1024)
        println("컴퓨터 시작 $data")
    }
}

/************************올바른 예시**********************/

//추상화된 인터페이스 (저수준 모듈)
interface StorageDevice {
    fun readData(): String
}

//구체적인 구현체
class HardDrive2 : StorageDevice {
    override fun readData(): String = "하드 드라이브에서 데이터 읽기"
}

class SSD2 : StorageDevice {
    override fun readData(): String = "SSD에서 데이터 읽기"
}

//고수준 모듈
class Computer3(private val storage: StorageDevice) {
    fun start() {
        val data = storage.readData()
        println("컴퓨터 시작 : $data")
    }
}

fun main() {
    val hardDrive = HardDrive2()
    val computerWithHDD = Computer3(hardDrive)
    computerWithHDD.start()

    val ssd = SSD2()
    val computerWithSSD = Computer3(ssd)
    computerWithSSD.start()
}