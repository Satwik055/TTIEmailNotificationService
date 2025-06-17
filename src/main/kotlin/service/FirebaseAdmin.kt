package service

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.slf4j.LoggerFactory
import java.io.FileInputStream

object FirebaseAdmin{
    private val logger = LoggerFactory.getLogger("Firebase Admin")

    fun initializeFirebaseAdmin() {
        val serviceAccount = FileInputStream("src/main/kotlin/firebase-admin-key.json")
        val options = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()
        FirebaseApp.initializeApp(options)
        logger.info("Firebase initialized")
    }
}
