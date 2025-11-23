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
            ),
            ContactResponse(
                11,
                "Tiago Sousa",
                "+351912345001",
                "tiago.sousa@example.com",
                "https://picsum.photos/200?11"
            ),
            ContactResponse(
                12,
                "Inês Carvalho",
                "+351912345002",
                "ines.carvalho@example.com",
                "https://picsum.photos/200?12"
            ),
            ContactResponse(
                13,
                "Pedro Fonseca",
                "+351912345003",
                "pedro.fonseca@example.com",
                "https://picsum.photos/200?13"
            ),
            ContactResponse(
                14,
                "Rita Moreira",
                "+351912345004",
                "rita.moreira@example.com",
                "https://picsum.photos/200?14"
            ),
            ContactResponse(
                15,
                "André Gomes",
                "+351912345005",
                "andre.gomes@example.com",
                "https://picsum.photos/200?15"
            ),
            ContactResponse(
                16,
                "Patrícia Nunes",
                "+351912345006",
                "patricia.nunes@example.com",
                "https://picsum.photos/200?16"
            ),
            ContactResponse(
                17,
                "Hugo Matos",
                "+351912345007",
                "hugo.matos@example.com",
                "https://picsum.photos/200?17"
            ),
            ContactResponse(
                18,
                "Daniela Faria",
                "+351912345008",
                "daniela.faria@example.com",
                "https://picsum.photos/200?18"
            ),
            ContactResponse(
                19,
                "Alexandre Rocha",
                "+351912345009",
                "alexandre.rocha@example.com",
                "https://picsum.photos/200?19"
            ),
            ContactResponse(
                20,
                "Leonor Batista",
                "+351912345010",
                "leonor.batista@example.com",
                "https://picsum.photos/200?20"
            ),

            ContactResponse(
                21,
                "Gustavo Neves",
                "+351912345011",
                "gustavo.neves@example.com",
                "https://picsum.photos/200?21"
            ),
            ContactResponse(
                22,
                "Mariana Lopes",
                "+351912345012",
                "mariana.lopes@example.com",
                "https://picsum.photos/200?22"
            ),
            ContactResponse(
                23,
                "Ricardo Correia",
                "+351912345013",
                "ricardo.correia@example.com",
                "https://picsum.photos/200?23"
            ),
            ContactResponse(
                24,
                "Catarina Duarte",
                "+351912345014",
                "catarina.duarte@example.com",
                "https://picsum.photos/200?24"
            ),
            ContactResponse(
                25,
                "João Cardoso",
                "+351912345015",
                "joao.cardoso@example.com",
                "https://picsum.photos/200?25"
            ),
            ContactResponse(
                26,
                "Sara Pinto",
                "+351912345016",
                "sara.pinto@example.com",
                "https://picsum.photos/200?26"
            ),
            ContactResponse(
                27,
                "Vítor Carvalho",
                "+351912345017",
                "vitor.carvalho@example.com",
                "https://picsum.photos/200?27"
            ),
            ContactResponse(
                28,
                "Helena Barros",
                "+351912345018",
                "helena.barros@example.com",
                "https://picsum.photos/200?28"
            ),
            ContactResponse(
                29,
                "Diogo Morais",
                "+351912345019",
                "diogo.morais@example.com",
                "https://picsum.photos/200?29"
            ),
            ContactResponse(
                30,
                "Filipa Mendes",
                "+351912345020",
                "filipa.mendes@example.com",
                "https://picsum.photos/200?30"
            ),

            ContactResponse(
                31,
                "Joana Rios",
                "+351912345021",
                "joana.rios@example.com",
                "https://picsum.photos/200?31"
            ),
            ContactResponse(
                32,
                "Nuno Pacheco",
                "+351912345022",
                "nuno.pacheco@example.com",
                "https://picsum.photos/200?32"
            ),
            ContactResponse(
                33,
                "Cláudia Vieira",
                "+351912345023",
                "claudia.vieira@example.com",
                "https://picsum.photos/200?33"
            ),
            ContactResponse(
                34,
                "Renato Tavares",
                "+351912345024",
                "renato.tavares@example.com",
                "https://picsum.photos/200?34"
            ),
            ContactResponse(
                35,
                "Marta Moura",
                "+351912345025",
                "marta.moura@example.com",
                "https://picsum.photos/200?35"
            ),
            ContactResponse(
                36,
                "Eduardo Silveira",
                "+351912345026",
                "eduardo.silveira@example.com",
                "https://picsum.photos/200?36"
            ),
            ContactResponse(
                37,
                "Clara Pimentel",
                "+351912345027",
                "clara.pimentel@example.com",
                "https://picsum.photos/200?37"
            ),
            ContactResponse(
                38,
                "Luís Monteiro",
                "+351912345028",
                "luis.monteiro@example.com",
                "https://picsum.photos/200?38"
            ),
            ContactResponse(
                39,
                "Tânia Freitas",
                "+351912345029",
                "tania.freitas@example.com",
                "https://picsum.photos/200?39"
            ),
            ContactResponse(
                40,
                "Fábio Ramos",
                "+351912345030",
                "fabio.ramos@example.com",
                "https://picsum.photos/200?40"
            ),

            ContactResponse(
                41,
                "Vera Dias",
                "+351912345031",
                "vera.dias@example.com",
                "https://picsum.photos/200?41"
            ),
            ContactResponse(
                42,
                "Marco Vale",
                "+351912345032",
                "marco.vale@example.com",
                "https://picsum.photos/200?42"
            ),
            ContactResponse(
                43,
                "Bruna Simões",
                "+351912345033",
                "bruna.simoes@example.com",
                "https://picsum.photos/200?43"
            ),
            ContactResponse(
                44,
                "António Barreira",
                "+351912345034",
                "antonio.barreira@example.com",
                "https://picsum.photos/200?44"
            ),
            ContactResponse(
                45,
                "Diana Castro",
                "+351912345035",
                "diana.castro@example.com",
                "https://picsum.photos/200?45"
            ),
            ContactResponse(
                46,
                "Luís Ventura",
                "+351912345036",
                "luis.ventura@example.com",
                "https://picsum.photos/200?46"
            ),
            ContactResponse(
                47,
                "Sónia Leal",
                "+351912345037",
                "sonia.leal@example.com",
                "https://picsum.photos/200?47"
            ),
            ContactResponse(
                48,
                "Carlos Henriques",
                "+351912345038",
                "carlos.henriques@example.com",
                "https://picsum.photos/200?48"
            ),
            ContactResponse(
                49,
                "Raquel Cordeiro",
                "+351912345039",
                "raquel.cordeiro@example.com",
                "https://picsum.photos/200?49"
            ),
            ContactResponse(
                50,
                "Fernando Reis",
                "+351912345040",
                "fernando.reis@example.com",
                "https://picsum.photos/200?50"
            ),

            ContactResponse(
                51,
                "Elisa Santiago",
                "+351912345041",
                "elisa.santiago@example.com",
                "https://picsum.photos/200?51"
            ),
            ContactResponse(
                52,
                "Samuel Dantas",
                "+351912345042",
                "samuel.dantas@example.com",
                "https://picsum.photos/200?52"
            ),
            ContactResponse(
                53,
                "Daniel Freire",
                "+351912345043",
                "daniel.freire@example.com",
                "https://picsum.photos/200?53"
            ),
            ContactResponse(
                54,
                "Fabiana Salgado",
                "+351912345044",
                "fabiana.salgado@example.com",
                "https://picsum.photos/200?54"
            ),
            ContactResponse(
                55,
                "Jorge Matias",
                "+351912345045",
                "jorge.matias@example.com",
                "https://picsum.photos/200?55"
            ),
            ContactResponse(
                56,
                "Célia Azevedo",
                "+351912345046",
                "celia.azevedo@example.com",
                "https://picsum.photos/200?56"
            ),
            ContactResponse(
                57,
                "Igor Marques",
                "+351912345047",
                "igor.marques@example.com",
                "https://picsum.photos/200?57"
            ),
            ContactResponse(
                58,
                "Mafalda Cunha",
                "+351912345048",
                "mafalda.cunha@example.com",
                "https://picsum.photos/200?58"
            ),
            ContactResponse(
                59,
                "Bruno Guedes",
                "+351912345049",
                "bruno.guedes@example.com",
                "https://picsum.photos/200?59"
            ),
            ContactResponse(
                60,
                "Carla Meireles",
                "+351912345050",
                "carla.meireles@example.com",
                "https://picsum.photos/200?60"
            ),

            ContactResponse(
                61,
                "Adriana Leme",
                "+351912345051",
                "adriana.leme@example.com",
                "https://picsum.photos/200?61"
            ),
            ContactResponse(
                62,
                "Augusto Faria",
                "+351912345052",
                "augusto.faria@example.com",
                "https://picsum.photos/200?62"
            ),
            ContactResponse(
                63,
                "Isabel Canto",
                "+351912345053",
                "isabel.canto@example.com",
                "https://picsum.photos/200?63"
            ),
            ContactResponse(
                64,
                "Rogério Rocha",
                "+351912345054",
                "rogerio.rocha@example.com",
                "https://picsum.photos/200?64"
            ),
            ContactResponse(
                65,
                "Jéssica Carvalho",
                "+351912345055",
                "jessica.carvalho@example.com",
                "https://picsum.photos/200?65"
            ),
            ContactResponse(
                66,
                "Márcio Torres",
                "+351912345056",
                "marcio.torres@example.com",
                "https://picsum.photos/200?66"
            ),
            ContactResponse(
                67,
                "Paula Nogueira",
                "+351912345057",
                "paula.nogueira@example.com",
                "https://picsum.photos/200?67"
            ),
            ContactResponse(
                68,
                "Hélder Brito",
                "+351912345058",
                "helder.brito@example.com",
                "https://picsum.photos/200?68"
            ),
            ContactResponse(
                69,
                "Tatiana Mourão",
                "+351912345059",
                "tatiana.mourao@example.com",
                "https://picsum.photos/200?69"
            ),
            ContactResponse(
                70,
                "Joaquim Sampaio",
                "+351912345060",
                "joaquim.sampaio@example.com",
                "https://picsum.photos/200?70"
            ),

            ContactResponse(
                71,
                "Cristiana Maia",
                "+351912345061",
                "cristiana.maia@example.com",
                "https://picsum.photos/200?71"
            ),
            ContactResponse(
                72,
                "Henrique Dias",
                "+351912345062",
                "henrique.dias@example.com",
                "https://picsum.photos/200?72"
            ),
            ContactResponse(
                73,
                "Carolina Loureiro",
                "+351912345063",
                "carolina.loureiro@example.com",
                "https://picsum.photos/200?73"
            ),
            ContactResponse(
                74,
                "Nélson Braga",
                "+351912345064",
                "nelson.braga@example.com",
                "https://picsum.photos/200?74"
            ),
            ContactResponse(
                75,
                "Joana Pires",
                "+351912345065",
                "joana.pires@example.com",
                "https://picsum.photos/200?75"
            ),
            ContactResponse(
                76,
                "Rui Figueiredo",
                "+351912345066",
                "rui.figueiredo@example.com",
                "https://picsum.photos/200?76"
            ),
            ContactResponse(
                77,
                "Lara Teixeira",
                "+351912345067",
                "lara.teixeira@example.com",
                "https://picsum.photos/200?77"
            ),
            ContactResponse(
                78,
                "Artur Cerqueira",
                "+351912345068",
                "artur.cerqueira@example.com",
                "https://picsum.photos/200?78"
            ),
            ContactResponse(
                79,
                "Daniela Sousa",
                "+351912345069",
                "daniela.sousa@example.com",
                "https://picsum.photos/200?79"
            ),
            ContactResponse(
                80,
                "Hugo Cruz",
                "+351912345070",
                "hugo.cruz@example.com",
                "https://picsum.photos/200?80"
            ),

            ContactResponse(
                81,
                "Sandra Afonso",
                "+351912345071",
                "sandra.afonso@example.com",
                "https://picsum.photos/200?81"
            ),
            ContactResponse(
                82,
                "Eduarda Ramos",
                "+351912345072",
                "eduarda.ramos@example.com",
                "https://picsum.photos/200?82"
            ),
            ContactResponse(
                83,
                "Miguel Fernandes",
                "+351912345073",
                "miguel.fernandes@example.com",
                "https://picsum.photos/200?83"
            ),
            ContactResponse(
                84,
                "Liliana Lopes",
                "+351912345074",
                "liliana.lopes@example.com",
                "https://picsum.photos/200?84"
            ),
            ContactResponse(
                85,
                "Vasco Almeida",
                "+351912345075",
                "vasco.almeida@example.com",
                "https://picsum.photos/200?85"
            ),
            ContactResponse(
                86,
                "Raquel Pinho",
                "+351912345076",
                "raquel.pinho@example.com",
                "https://picsum.photos/200?86"
            ),
            ContactResponse(
                87,
                "Duarte Neves",
                "+351912345077",
                "duarte.neves@example.com",
                "https://picsum.photos/200?87"
            ),
            ContactResponse(
                88,
                "Sílvia Mendonça",
                "+351912345078",
                "silvia.mendonca@example.com",
                "https://picsum.photos/200?88"
            ),
            ContactResponse(
                89,
                "Filipe Amaral",
                "+351912345079",
                "filipe.amaral@example.com",
                "https://picsum.photos/200?89"
            ),
            ContactResponse(
                90,
                "Camila Duarte",
                "+351912345080",
                "camila.duarte@example.com",
                "https://picsum.photos/200?90"
            ),

            ContactResponse(
                91,
                "Otávio Ribeiro",
                "+351912345081",
                "otavio.ribeiro@example.com",
                "https://picsum.photos/200?91"
            ),
            ContactResponse(
                92,
                "Letícia Morais",
                "+351912345082",
                "leticia.morais@example.com",
                "https://picsum.photos/200?92"
            ),
            ContactResponse(
                93,
                "Bernardo Teles",
                "+351912345083",
                "bernardo.teles@example.com",
                "https://picsum.photos/200?93"
            ),
            ContactResponse(
                94,
                "Cíntia Vale",
                "+351912345084",
                "cintia.vale@example.com",
                "https://picsum.photos/200?94"
            ),
            ContactResponse(
                95,
                "Alex Ramos",
                "+351912345085",
                "alex.ramos@example.com",
                "https://picsum.photos/200?95"
            ),
            ContactResponse(
                96,
                "Tamara Reis",
                "+351912345086",
                "tamara.reis@example.com",
                "https://picsum.photos/200?96"
            ),
            ContactResponse(
                97,
                "Fábio Moreira",
                "+351912345087",
                "fabio.moreira@example.com",
                "https://picsum.photos/200?97"
            ),
            ContactResponse(
                98,
                "Vanessa Maia",
                "+351912345088",
                "vanessa.maia@example.com",
                "https://picsum.photos/200?98"
            ),
            ContactResponse(
                99,
                "César Andrade",
                "+351912345089",
                "cesar.andrade@example.com",
                "https://picsum.photos/200?99"
            ),
            ContactResponse(
                100,
                "Laura Cardoso",
                "+351912345090",
                "laura.cardoso@example.com",
                "https://picsum.photos/200?100"
            ),

            ContactResponse(
                101,
                "Afonso Leite",
                "+351912345091",
                "afonso.leite@example.com",
                "https://picsum.photos/200?101"
            ),
            ContactResponse(
                102,
                "Melissa Costa",
                "+351912345092",
                "melissa.costa@example.com",
                "https://picsum.photos/200?102"
            ),
            ContactResponse(
                103,
                "Rafael Pires",
                "+351912345093",
                "rafael.pires@example.com",
                "https://picsum.photos/200?103"
            ),
            ContactResponse(
                104,
                "Bianca Silva",
                "+351912345094",
                "bianca.silva@example.com",
                "https://picsum.photos/200?104"
            ),
            ContactResponse(
                105,
                "Tomás Oliveira",
                "+351912345095",
                "tomas.oliveira@example.com",
                "https://picsum.photos/200?105"
            ),
            ContactResponse(
                106,
                "Nádia Dias",
                "+351912345096",
                "nadia.dias@example.com",
                "https://picsum.photos/200?106"
            ),
            ContactResponse(
                107,
                "Gilberto Sampaio",
                "+351912345097",
                "gilberto.sampaio@example.com",
                "https://picsum.photos/200?107"
            ),
            ContactResponse(
                108,
                "Amanda Nery",
                "+351912345098",
                "amanda.nery@example.com",
                "https://picsum.photos/200?108"
            ),
            ContactResponse(
                109,
                "Rodrigo Fonseca",
                "+351912345099",
                "rodrigo.fonseca@example.com",
                "https://picsum.photos/200?109"
            ),
            ContactResponse(
                110,
                "Érica Malta",
                "+351912345100",
                "erica.malta@example.com",
                "https://picsum.photos/200?110"
            ),

            ContactResponse(
                111,
                "Fabrício Coelho",
                "+351912345101",
                "fabricio.coelho@example.com",
                "https://picsum.photos/200?111"
            ),
            ContactResponse(
                112,
                "Carina Rocha",
                "+351912345102",
                "carina.rocha@example.com",
                "https://picsum.photos/200?112"
            ),
            ContactResponse(
                113,
                "Rui Azevedo",
                "+351912345103",
                "rui.azevedo@example.com",
                "https://picsum.photos/200?113"
            ),
            ContactResponse(
                114,
                "Yasmin Freitas",
                "+351912345104",
                "yasmin.freitas@example.com",
                "https://picsum.photos/200?114"
            ),
            ContactResponse(
                115,
                "Orlando Nunes",
                "+351912345105",
                "orlando.nunes@example.com",
                "https://picsum.photos/200?115"
            ),
            ContactResponse(
                116,
                "Carolina Esteves",
                "+351912345106",
                "carolina.esteves@example.com",
                "https://picsum.photos/200?116"
            ),
            ContactResponse(
                117,
                "Benjamin Reis",
                "+351912345107",
                "benjamin.reis@example.com",
                "https://picsum.photos/200?117"
            ),
            ContactResponse(
                118,
                "Juliana Correia",
                "+351912345108",
                "juliana.correia@example.com",
                "https://picsum.photos/200?118"
            ),
            ContactResponse(
                119,
                "Eduardo Macedo",
                "+351912345109",
                "eduardo.macedo@example.com",
                "https://picsum.photos/200?119"
            ),
            ContactResponse(
                120,
                "Natália Brito",
                "+351912345110",
                "natalia.brito@example.com",
                "https://picsum.photos/200?120"
            ),

            ContactResponse(
                121,
                "Kevin Monteiro",
                "+351912345111",
                "kevin.monteiro@example.com",
                "https://picsum.photos/200?121"
            ),
            ContactResponse(
                122,
                "Cecília Matos",
                "+351912345112",
                "cecilia.matos@example.com",
                "https://picsum.photos/200?122"
            ),
            ContactResponse(
                123,
                "Renan Cruz",
                "+351912345113",
                "renan.cruz@example.com",
                "https://picsum.photos/200?123"
            ),
            ContactResponse(
                124,
                "Gisela Ribeiro",
                "+351912345114",
                "gisela.ribeiro@example.com",
                "https://picsum.photos/200?124"
            ),
            ContactResponse(
                125,
                "Fábio Correia",
                "+351912345115",
                "fabio.correia@example.com",
                "https://picsum.photos/200?125"
            ),
            ContactResponse(
                126,
                "Patrícia Araújo",
                "+351912345116",
                "patricia.araujo@example.com",
                "https://picsum.photos/200?126"
            ),
            ContactResponse(
                127,
                "Diego Castro",
                "+351912345117",
                "diego.castro@example.com",
                "https://picsum.photos/200?127"
            ),
            ContactResponse(
                128,
                "Mónica Franco",
                "+351912345118",
                "monica.franco@example.com",
                "https://picsum.photos/200?128"
            ),
            ContactResponse(
                129,
                "Rafael Morais",
                "+351912345119",
                "rafael.morais@example.com",
                "https://picsum.photos/200?129"
            ),
            ContactResponse(
                130,
                "Helena Leite",
                "+351912345120",
                "helena.leite@example.com",
                "https://picsum.photos/200?130"
            ),

            ContactResponse(
                userId = 998,
                name = "Mario Alberto 2",
                phone = "+351911234567",
                email = "mario.alberto2@example.com",
                avatarUrl = "https://picsum.photos/200?12"
            ),
            ContactResponse(
                userId = 999,
                name = "Mario Alberto",
                phone = "+351911234567",
                email = "mario.alberto@example.com",
                avatarUrl = "https://picsum.photos/200?13"
            )
        )
    )
}
