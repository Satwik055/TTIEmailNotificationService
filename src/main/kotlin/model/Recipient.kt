package model

import kotlinx.serialization.Serializable

@Serializable
data class Recipient(
    val id:Int = 0,
    val name:String = "",
    val account_number:String = "",
    val ifsc_code:String = "",
    val bank:String = "",
    val email:String = ""
)
