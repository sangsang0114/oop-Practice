package behavior

abstract class Game {
    //템플릿 메서드는 final로 선언하여 오버라이드 되지 않도록 한다.
    public fun play() {
        initialize()
        startPlay()
        endPlay()
    }

    protected abstract fun initialize()
    protected abstract fun startPlay()
    protected abstract fun endPlay()
}

class Chess : Game() {
    override fun initialize() {
        println("체스 게임을 초기화합니다")
    }

    override fun startPlay() {
        println("체스 게임을 시작합니다")
    }

    override fun endPlay() {
        println("체스 게임을 종료합니다")
    }
}

class Football : Game() {
    override fun initialize() {
        println("축구 게임을 초기화합니다")
    }

    override fun startPlay() {
        println("축구 게임을 시작합니다")
    }

    override fun endPlay() {
        println("축구 게임을 종료합니다")
    }
}

