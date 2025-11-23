package com.marsn.minitalk.core.domain

import com.marsn.minitalk.core.domain.contact.Contact

data class MessageContact(
    val contact: Contact,
    val message: Message
) {}