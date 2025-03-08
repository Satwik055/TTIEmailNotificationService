package model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val ttf_user_id:Int = 0,
    val name:String = "",
    val email:String = "",
    val phone:String = "",
    val kyc_status:Boolean = false,
    val email_verified: Boolean = true,
    val phone_verified: Boolean =false,
    val sub: String = "",
    val fcm_token:String = "",
    val preferred_currency:CurrencyType = CurrencyType.EUR
)
