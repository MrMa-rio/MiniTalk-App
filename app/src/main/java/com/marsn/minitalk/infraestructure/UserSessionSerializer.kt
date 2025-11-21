package com.marsn.minitalk.infraestructure

import androidx.datastore.core.Serializer
import com.marsn.minitalk.core.domain.UserSession
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object UserSessionSerializer : Serializer<UserSession?> {
    override val defaultValue: UserSession? = null

    override suspend fun readFrom(input: InputStream): UserSession? =
        try {
            Json.decodeFromString(
                UserSession.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (e: Exception) {
            null
        }

    override suspend fun writeTo(
        t: UserSession?,
        output: OutputStream
    ) = output.write(Json.encodeToString(UserSession.serializer(), t as UserSession).encodeToByteArray())
}
