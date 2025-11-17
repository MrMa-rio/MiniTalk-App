package com.marsn.minitalk.core.dataprovider.repository.users

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
class UserEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val email: String
) {}