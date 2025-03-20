package service

import java.util.logging.Logger
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import model.MessageTemplate


object PushMessagingService{
    private val logger: Logger = Logger.getLogger("Push Message Service")

    fun sendPushNotification(deviceToken: String, template: MessageTemplate) {
        val notification = Notification.builder()
            .setTitle(template.title)
            .setBody(template.body)
            .build()
        val message = Message.builder()
            .setToken(deviceToken)
            .setNotification(notification)
            .build()
        try {
            val response = FirebaseMessaging.getInstance().send(message)
            logger.info("Successfully sent message: $response")
        } catch (e: Exception) {
            logger.info("Error sending message: ${e.message}")
        }
    }
}