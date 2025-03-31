package service

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import model.Profile
import model.Recipient


object UserService{
    suspend fun getFcmToken(userId: Int, supabaseClient: SupabaseClient): String {
        val profile = supabaseClient.from("ttfuser")
            .select {
                filter {
                    eq("ttf_user_id", userId)
                }
            }
            .decodeSingle<Profile>()
        return profile.fcm_token
    }

    suspend fun getProfile(userId: Int, supabaseClient: SupabaseClient): Profile {
        val profile = supabaseClient.from("ttfuser")
            .select {
                filter {
                    eq("ttf_user_id", userId)
                }
            }
            .decodeSingle<Profile>()
        return profile
    }

    suspend fun getRecipient(recipientId: Int, supabaseClient: SupabaseClient): Recipient {
        val recipient = supabaseClient.from("recipient")
            .select {
                filter {
                    eq("id", recipientId)
                }
            }
            .decodeSingle<Recipient>()
        return recipient
    }
}