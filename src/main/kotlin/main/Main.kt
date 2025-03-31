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
import model.CurrencyType
import model.Transaction
import model.TransactionStatus
import service.*
import util.*
import java.util.logging.Logger

private val logger: Logger = Logger.getLogger("Root Logger")

suspend fun listenTransactionStatusChanges(supabaseClient: SupabaseClient){
    FirebaseAdmin.initializeFirebaseAdmin()

    val adminEmail = "sappidishyamsundharreddy@gmail.com"
    val channel = supabaseClient.channel("transaction_channel")
    val changeFlow = channel.postgresChangeFlow<PostgresAction.Update>(schema = "public") {
        table = "transaction"
    }
    val insertFlow = channel.postgresChangeFlow<PostgresAction.Insert>(schema = "public") {
        table = "transaction"
    }

    insertFlow.onEach { insertion ->
        val json = Json{ignoreUnknownKeys = true}
        val transaction = json.decodeFromString<Transaction>(insertion.record.toString())

        val newTransactionEmailTemplate = getNewTransactionEmailTemplate(
            transactionCode = transaction.transaction_code,
            amount = transaction.sent.toString(),
            time = formatTimestamp(transaction.date),
            currency = CurrencyType.USD,
            status = transaction.status,
            username = UserService.getProfile(transaction.sender_id, supabaseClient).name
        )

        EmailService.sendEmail(
            recipientAddress = transaction.email,
            template = newTransactionEmailTemplate
        )


        val senderProfile = UserService.getProfile(transaction.sender_id, supabaseClient)
        val recipientProfile = UserService.getRecipient(transaction.recipient_id, supabaseClient)

        val transactionAdminNotifyTemplate = getAdminNotifyEmailTemplate(
            transactionCode = transaction.transaction_code,
            currency = transaction.currency,
            senderName = senderProfile.name,
            reason = transaction.reason,
            accountNumber = recipientProfile.account_number,
            recipientName = recipientProfile.name,
            paymentScreenshotLink = transaction.screenshot,
            ifscCode = recipientProfile.ifsc_code,
            time = formatTimestamp(transaction.date),
            amountSent = transaction.sent.toString(),
            bank = recipientProfile.bank,
            amountReceive = transaction.receive.toString(),
            status = transaction.status,
            senderEmail = senderProfile.email,
            senderPhone = senderProfile.phone,
        )
        EmailService.sendEmail(
            recipientAddress = adminEmail,
            template = transactionAdminNotifyTemplate
        )

    }.launchIn(CoroutineScope(currentCoroutineContext()))

    changeFlow.onEach { update ->
        val json = Json{ignoreUnknownKeys = true}
        val transaction = json.decodeFromString<Transaction>(update.record.toString())
        logger.info("Updated transaction status with of id ${transaction.id} to: ${transaction.status}")

        val fcmDeviceToken = UserService.getFcmToken(transaction.sender_id, supabaseClient)
        val messageTemplate = getTransactionUpdateMessageTemplate(
            status = transaction.status.name,
            transactionCode = transaction.transaction_code
        )
        PushMessagingService.sendPushNotification(
            deviceToken = fcmDeviceToken,
            template = messageTemplate
        )

        val transactionUpdateEmailTemplate = getTransactionUpdateEmailTemplate(
            username = UserService.getProfile(
                userId = transaction.sender_id,
                supabaseClient = supabaseClient
            ).name,
            transactionCode = transaction.transaction_code,
            amount = transaction.sent.toString(),
            time = formatTimestamp(transaction.date),
            status = transaction.status,
            currency = transaction.currency
        )
        EmailService.sendEmail(
            recipientAddress = transaction.email,
            template = transactionUpdateEmailTemplate
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