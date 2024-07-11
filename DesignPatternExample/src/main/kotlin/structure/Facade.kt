package structure

interface MessageSender {
    fun send(message: String)
}

class EmailSender : MessageSender {
    override fun send(message: String) {
        println("Sending email : $message")
    }
}

class SmsSender : MessageSender {
    override fun send(message: String) {
        println("Sending sms : $message")
    }
}

class MessengerService(private val sender: MessageSender) {
    fun send(message: String) {
        sender.send(message)
    }
}

fun FacadeMain() {
    val emailService = MessengerService(EmailSender())
    emailService.send("Hello via email")

    val smsService = MessengerService(SmsSender())
    smsService.send("Hello via SMS")
}