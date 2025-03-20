import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.runBlocking
import model.MessageTemplate
import model.Profile
import util.getTransactionUpdateMessageTemplate
import java.io.FileInputStream
import java.util.logging.Logger


private val logger: Logger = Logger.getLogger("Push Message Logger")



fun initializeFirebaseAdmin() {


    val serviceAccount = FileInputStream("src/main/kotlin/firebase-admin-key.json")
    val options = FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build()
    FirebaseApp.initializeApp(options)
}

fun sendPushNotification(deviceToken: String, template: MessageTemplate ) {
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

suspend fun getFcmToken(userId:Int, supabaseClient: SupabaseClient): String {
    val profile = supabaseClient.from("ttfuser")
        .select{filter {
            eq("ttf_user_id", userId)
        }}
        .decodeSingle<Profile>()
    return profile.fcm_token
}

