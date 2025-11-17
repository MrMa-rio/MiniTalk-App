package com.marsn.minitalk.core.dataprovider.repository.converters

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.shared.enums.TypeConversation
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

class Converters {

    @TypeConverter
    fun fromStringList(list: List<String>?): String {
        return list?.joinToString(",") ?: ""
    }

    @TypeConverter
    fun toStringList(data: String?): List<String> {
        if (data.isNullOrEmpty()) return emptyList()
        return data.split(",")
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? = uuid?.toString()

    @TypeConverter
    fun toUUID(uuid: String?): UUID? = uuid?.let { UUID.fromString(it) }

    @TypeConverter
    fun toTypeConversation(value: String) = enumValueOf<TypeConversation>(value)

    @TypeConverter
    fun fromTypeConversation(value: TypeConversation) = value.name


    @TypeConverter
    fun fromStringListConversation(list: List<Conversation>?): String {
        return list?.joinToString(",") ?: ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): String? {
        return dateTime?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss")) // Ex: "2025-11-17T17:42:52.123"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDateTime(dateTimeString: String?): LocalDateTime? {
        // 2. Fazer o parse da string de volta para LocalDateTime.
        return dateTimeString?.let {
            LocalDateTime.parse(it, DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss"))
        }
    }


}