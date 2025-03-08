import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.*
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.json.Json
import model.Transaction
import util.getTransactionUpdateTemplate
import util.sendEmail
import java.util.logging.Logger

val supabase =  createSupabaseClient(
    supabaseUrl = "https://fqsnwalptczelvhiwohd.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZxc253YWxwdGN6ZWx2aGl3b2hkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzg2NzI4MTYsImV4cCI6MjA1NDI0ODgxNn0.TzD0KcPnEJz0DvLYxUmK68PeDuNy47sU0jRlyhAls-I"
) {
    install(Postgrest)
    install(Realtime)
    install(Storage)
}

val logger: Logger = Logger.getLogger("Root Logger")


suspend fun listenTransactionStatusChanges(){
    val channel = supabase.channel("transaction_channel")
    val changeFlow = channel.postgresChangeFlow<PostgresAction.Update>(schema = "public") {
        table = "transaction"
    }
    changeFlow.onEach { update ->
        val transaction = Json.decodeFromString<Transaction>(update.record.toString())
        logger.info("Updated transaction status with of id ${transaction.id} to: ${transaction.status}")
        sendEmail(
            recipientAddress = transaction.email,
            template = getTransactionUpdateTemplate(transactionCode = transaction.transaction_code, status = transaction.status.name))
    }.launchIn(CoroutineScope(currentCoroutineContext()))

    channel.subscribe()
}

fun main() = runBlocking{
    listenTransactionStatusChanges()
}