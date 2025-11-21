package com.marsn.minitalk.ui.mocks.contacts

import com.marsn.minitalk.core.domain.contact.ContactResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val mocksContactResponseFlow: Flow<List<ContactResponse>> = flow {
    emit(
        listOf(
            ContactResponse(
                userId = 1,
                name = "Maria Silva",
                phone = "+351912345678",
                email = "maria.silva@example.com",
                avatarUrl = "https://picsum.photos/200?1"
            ),
            ContactResponse(
                userId = 2,
                name = "João Pereira",
                phone = "+351913456789",
                email = "joao.pereira@example.com",
                avatarUrl = "httpsum.photos/200?2"
            ),
            ContactResponse(
                userId = 3,
                name = "Ana Rodrigues",
                phone = "+351914567890",
                email = "ana.rodrigues@example.com",
                avatarUrl = "https://picsum.photos/200?3"
            ),
            ContactResponse(
                userId = 4,
                name = "Miguel Santos",
                phone = "+351915678901",
                email = "miguel.santos@example.com",
                avatarUrl = "https://picsum.photos/200?4"
            ),
            ContactResponse(
                userId = 5,
                name = "Carla Ferreira",
                phone = "+351916789012",
                email = "carla.ferreira@example.com",
                avatarUrl = "https://picsum.photos/200?5"
            ),
            ContactResponse(
                userId = 6,
                name = "Rafael Almeida",
                phone = "+351917890123",
                email = "rafael.almeida@example.com",
                avatarUrl = "https://picsum.photos/200?6"
            ),
            ContactResponse(
                userId = 7,
                name = "Beatriz Costa",
                phone = "+351918901234",
                email = "beatriz.costa@example.com",
                avatarUrl = "https://picsum.photos/200?7"
            ),
            ContactResponse(
                userId = 8,
                name = "Paulo Oliveira",
                phone = "+351919012345",
                email = "paulo.oliveira@example.com",
                avatarUrl = "https://picsum.photos/200?8"
            ),
            ContactResponse(
                userId = 9,
                name = "Sofia Ribeiro",
                phone = "+351910123456",
                email = "sofia.ribeiro@example.com",
                avatarUrl = "https://picsum.photos/200?9"
            ),
            ContactResponse(
                userId = 10,
                name = "Bruno Martins",
                phone = "+351911234567",
                email = "bruno.martins@example.com",
                avatarUrl = "https://picsum.photos/200?10"
            )
        )
    )
}
