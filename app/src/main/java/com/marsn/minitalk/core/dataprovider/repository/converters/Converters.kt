package com.marsn.minitalk.core.dataprovider.repository.converters

import androidx.room.TypeConverter
import com.marsn.minitalk.core.domain.Conversation
import com.marsn.minitalk.core.shared.enums.TypeConversation
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
    fun fromTyConversation(value: TypeConversation) = value.name


    @TypeConverter
    fun fromStringListConversation(list: List<Conversation>?): String {
        return list?.joinToString(",") ?: ""
    }



}