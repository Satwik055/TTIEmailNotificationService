package main

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.*
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.json.Json
import model.Transaction
import service.*
import util.getAdminNotifyEmailTemplate
import util.getTransactionUpdateMessageTemplate
import util.getTransactionUpdateEmailTemplate
import java.util.logging.Logger

private val logger: Logger = Logger.getLogger("Root Logger")

suspend fun listenTransactionStatusChanges(supabaseClient: SupabaseClient){
    FirebaseAdmin.initializeFirebaseAdmin()

    val channel = supabaseClient.channel("transaction_channel")
    val changeFlow = channel.postgresChangeFlow<PostgresAction.Update>(schema = "public") {
        table = "transaction"
    }
    changeFlow.onEach { update ->
        val json = Json{ignoreUnknownKeys = true}
        val transaction = json.decodeFromString<Transaction>(update.record.toString())
        logger.info("Updated transaction status with of id ${transaction.id} to: ${transaction.status}")

        val fcmDeviceToken = UserService.getFcmToken(transaction.sender_id, supabaseClient)
        val messageTemplate = getTransactionUpdateMessageTemplate(transaction.status.name, transaction.transaction_code)
        PushMessagingService.sendPushNotification(
            deviceToken = fcmDeviceToken,
            template = messageTemplate
        )

        val transactionEmailTemplate = getTransactionUpdateEmailTemplate(transactionCode = transaction.transaction_code, status = transaction.status.name)
        EmailService.sendEmail(
            recipientAddress = transaction.email,
            template = transactionEmailTemplate
        )

        val transactionAdminNotifyTemplate = getAdminNotifyEmailTemplate(transaction.transaction_code)
        EmailService.sendEmail(
            recipientAddress = "satwikkumar055@gmail.com",
            template = transactionAdminNotifyTemplate
        )

    }.launchIn(CoroutineScope(currentCoroutineContext()))
    channel.subscribe()
}
fun main() = runBlocking {
    val supabase =  createSupabaseClient(
        supabaseUrl = "https://fqsnwalptczelvhiwohd.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZxc253YWxwdGN6ZWx2aGl3b2hkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzg2NzI4MTYsImV4cCI6MjA1NDI0ODgxNn0.TzD0KcPnEJz0DvLYxUmK68PeDuNy47sU0jRlyhAls-I"
    ) {
        install(Postgrest)
        install(Realtime)
        install(Storage)
    }

    listenTransactionStatusChanges(supabase)
}