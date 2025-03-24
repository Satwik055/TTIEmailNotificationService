package model

import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val id:Int,
    val transaction_code:String,
    val email:String,
    val sent:Int,
    val receive:Int,
    val currency: CurrencyType,
    val status: TransactionStatus,
    val date:String,
    val reason:String,
    val sender_id:Int,
    val screenshot:String,
    val recipient_id:Int
)

