package service

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import model.Profile


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
}