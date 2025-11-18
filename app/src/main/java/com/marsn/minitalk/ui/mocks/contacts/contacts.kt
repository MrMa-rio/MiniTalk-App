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
                phoneNumber = "+351912345678",
                email = "maria.silva@example.com",
                address = "Rua das Flores 123, Lisboa",
                profilePicture = "https://picsum.photos/200?1"
            ),
            ContactResponse(
                userId = 2,
                name = "João Pereira",
                phoneNumber = "+351913456789",
                email = "joao.pereira@example.com",
                address = "Avenida Central 87, Porto",
                profilePicture = "httpsum.photos/200?2"
            ),
            ContactResponse(
                userId = 3,
                name = "Ana Rodrigues",
                phoneNumber = "+351914567890",
                email = "ana.rodrigues@example.com",
                address = "Travessa do Sol 45, Coimbra",
                profilePicture = "https://picsum.photos/200?3"
            ),
            ContactResponse(
                userId = 4,
                name = "Miguel Santos",
                phoneNumber = "+351915678901",
                email = "miguel.santos@example.com",
                address = "Rua Verde 55, Braga",
                profilePicture = "https://picsum.photos/200?4"
            ),
            ContactResponse(
                userId = 5,
                name = "Carla Ferreira",
                phoneNumber = "+351916789012",
                email = "carla.ferreira@example.com",
                address = "Rua Nova 800, Faro",
                profilePicture = "https://picsum.photos/200?5"
            ),
            ContactResponse(
                userId = 6,
                name = "Rafael Almeida",
                phoneNumber = "+351917890123",
                email = "rafael.almeida@example.com",
                address = "Praça do Mercado 12, Aveiro",
                profilePicture = "https://picsum.photos/200?6"
            ),
            ContactResponse(
                userId = 7,
                name = "Beatriz Costa",
                phoneNumber = "+351918901234",
                email = "beatriz.costa@example.com",
                address = "Rua do Mar 67, Setúbal",
                profilePicture = "https://picsum.photos/200?7"
            ),
            ContactResponse(
                userId = 8,
                name = "Paulo Oliveira",
                phoneNumber = "+351919012345",
                email = "paulo.oliveira@example.com",
                address = "Avenida Atlântica 300, Cascais",
                profilePicture = "https://picsum.photos/200?8"
            ),
            ContactResponse(
                userId = 9,
                name = "Sofia Ribeiro",
                phoneNumber = "+351910123456",
                email = "sofia.ribeiro@example.com",
                address = "Rua da Liberdade 200, Sintra",
                profilePicture = "https://picsum.photos/200?9"
            ),
            ContactResponse(
                userId = 10,
                name = "Bruno Martins",
                phoneNumber = "+351911234567",
                email = "bruno.martins@example.com",
                address = "Largo Central 9, Évora",
                profilePicture = "https://picsum.photos/200?10"
            )
        )
    )
}
