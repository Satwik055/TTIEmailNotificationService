package service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import model.MessageTemplate
import org.slf4j.Logger
import org.slf4j.LoggerFactory


object PushMessagingService{
    private val logger: Logger = LoggerFactory.getLogger("Push Message Service")

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
            logger.error("Error sending message: ${e.message}")
        }
    }
}