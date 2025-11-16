package com.marsn.minitalk.ui.mocks.messages

import com.marsn.minitalk.core.domain.MessageText

val messagesMock: List<MessageText> = listOf(



/*
    MessageText("Bom dia!", 1, 1737000000000, 101),
    MessageText("Bom diaaa", 2, 1737000060000, 202),
    MessageText("Acordou cedo hoje 😂", 3, 1737000110000, 202),
    MessageText("Nem tanto kkkk", 4, 1737000180000, 101),
    MessageText("Dormiu bem?", 5, 1737000240000, 101),
    MessageText("Mais ou menos 😴", 6, 1737000300000, 202),
    MessageText("E tu?", 7, 1737000320000, 202),
    MessageText("Dormi ok", 8, 1737000400000, 101),
    MessageText("Bora trabalhar?", 9, 1737000460000, 101),
    MessageText("Infelizmente sim kkk", 10, 1737000520000, 202),

    MessageText("Já comeu?", 11, 1737000580000, 101),
    MessageText("Ainda não 😬", 12, 1737000615000, 202),
    MessageText("Vai comer o que?", 13, 1737000672000, 101),
    MessageText("Pão e café msm kkk", 14, 1737000740000, 202),
    MessageText("Clássico 😂", 15, 1737000802000, 101),

    MessageText("Tu vai sair hoje?", 16, 1737000865000, 202),
    MessageText("Talvez, por quê?", 17, 1737000923000, 101),
    MessageText("Pra saber só", 18, 1737000979000, 202),
    MessageText("Entendi kk", 19, 1737001025000, 101),

    MessageText("Pegou a parada que eu mandei ontem?", 20, 1737001082000, 202),
    MessageText("Peguei sim", 21, 1737001127000, 101),
    MessageText("Serviu certinho", 22, 1737001189000, 101),
    MessageText("Nicee", 23, 1737001246000, 202),

    MessageText("Vai almoçar o que hoje?", 24, 1737001300000, 202),
    MessageText("Sem ideia ainda kkk", 25, 1737001360000, 101),
    MessageText("Talvez marmita", 26, 1737001423000, 101),
    MessageText("Eu tbm", 27, 1737001489000, 202),

    MessageText("Tá frio aí?", 28, 1737001553000, 202),
    MessageText("Um pouco", 29, 1737001600000, 101),
    MessageText("Aqui tá muito 😩", 30, 1737001658000, 202),

    MessageText("Tu viu a mensagem ontem?", 31, 1737001712000, 101),
    MessageText("Qual delas?", 32, 1737001777000, 202),
    MessageText("A do grupo", 33, 1737001830000, 101),
    MessageText("Ahh vi sim", 34, 1737001876000, 202),

    MessageText("E aí, vai participar?", 35, 1737001924000, 101),
    MessageText("Talvez kkk", 36, 1737001979000, 202),
    MessageText("Tu vai?", 37, 1737002028000, 202),
    MessageText("Acho que sim", 38, 1737002086000, 101),

    MessageText("Tô com sono ainda mano", 39, 1737002139000, 202),
    MessageText("Eu também 😴", 40, 1737002190000, 101),

    MessageText("Já abriu o PC?", 41, 1737002242000, 202),
    MessageText("Sim, ligando tudo aqui", 42, 1737002300000, 101),
    MessageText("Boa", 43, 1737002363000, 202),

    MessageText("Tô indo fazer café", 44, 1737002420000, 202),
    MessageText("Trás um pra mim tbm kkk", 45, 1737002485000, 101),
    MessageText("Jamais 😂", 46, 1737002534000, 202),

    MessageText("😂😂😂", 47, 1737002588000, 101),

    // Continuação — curtinhas, rápidas, estilo WhatsApp:

    MessageText("Como tá o clima aí?", 48, 1737002646000, 101),
    MessageText("Meio nublado", 49, 1737002703000, 202),
    MessageText("Aqui também", 50, 1737002762000, 101),
    MessageText("Chuva vindo já", 51, 1737002818000, 202),

    MessageText("Tu vai treinar hoje?", 52, 1737002870000, 202),
    MessageText("Acho que vou sim", 53, 1737002925000, 101),
    MessageText("Boa", 54, 1737002969000, 202),

    MessageText("Tô ouvindo música já", 55, 1737003020000, 202),
    MessageText("Qual?", 56, 1737003073000, 101),
    MessageText("Trap aleatório kkk", 57, 1737003129000, 202),
    MessageText("Nice", 58, 1737003174000, 101),

    MessageText("Vou entrar numa call aqui", 59, 1737003223000, 101),
    MessageText("Boa", 60, 1737003276000, 202),

    MessageText("Voltei", 61, 1737003342000, 101),
    MessageText("Demorou kk", 62, 1737003398000, 202),
    MessageText("Call chata", 63, 1737003449000, 101),
    MessageText("Sempre é 😂", 64, 1737003502000, 202),

    MessageText("Tu come agora?", 65, 1737003560000, 202),
    MessageText("Daqui a pouco", 66, 1737003617000, 101),
    MessageText("Eu tbm", 67, 1737003669000, 202),

    MessageText("Tá fazendo o que?", 68, 1737003720000, 202),
    MessageText("Respondendo email", 69, 1737003780000, 101),
    MessageText("Que ódio disso kkk", 70, 1737003841000, 202),

    MessageText("Kkkkkk", 71, 1737003895000, 101),
    MessageText("Tô indo ali rapidão", 72, 1737003948000, 202),
    MessageText("Ok", 73, 1737003993000, 101),

    MessageText("Voltei", 74, 1737004060000, 202),
    MessageText("Foi rápido", 75, 1737004113000, 101),

    MessageText("Viu o vídeo que mandei?", 76, 1737004172000, 202),
    MessageText("Ainda não, vou ver", 77, 1737004230000, 101),

    MessageText("É engraçado kkk", 78, 1737004283000, 202),

    MessageText("Vi agora 😂😂😂", 79, 1737004349000, 101),

    MessageText("Sabia kkk", 80, 1737004392000, 202),

    MessageText("Tô com fome já", 81, 1737004450000, 202),
    MessageText("Vai comer então kkk", 82, 1737004513000, 101),
    MessageText("Daqui a pouco", 83, 1737004566000, 202),

    MessageText("Preciso mandar um áudio depois", 84, 1737004621000, 101),
    MessageText("Pode mandar", 85, 1737004689000, 202),

    MessageText("Baixou o app que te falei?", 86, 1737004742000, 101),
    MessageText("Ainda não kkk", 87, 1737004805000, 202),
    MessageText("Baixa aí", 88, 1737004857000, 101),
    MessageText("Vou ver", 89, 1737004912000, 202),

    MessageText("Tá frio aqui agora", 90, 1737004966000, 202),
    MessageText("Liga o casaco kkk", 91, 1737005019000, 101),
    MessageText("Já tô com ele 😂", 92, 1737005078000, 202),

    MessageText("Boa", 93, 1737005133000, 101),

    MessageText("Preciso resolver um negócio aqui rapidão", 94, 1737005189000, 101),
    MessageText("Vai lá", 95, 1737005242000, 202),

    MessageText("Voltei", 96, 1737005304000, 101),
    MessageText("Deu certo?", 97, 1737005359000, 202),
    MessageText("Deu sim", 98, 1737005402000, 101),

    MessageText("Top", 99, 1737005450000, 202),

    MessageText("Vou almoçar agora", 100, 1737005508000, 202),
    MessageText("Bom almoço!", 101, 1737005574000, 101),
    MessageText("Tmj", 102, 1737005628000, 202),

    MessageText("Vou também", 103, 1737005685000, 101),
    MessageText("Boa!", 104, 1737005735000, 202),

    MessageText("Voltei", 105, 1737006400000, 202),
    MessageText("Eu também", 106, 1737006450000, 101),

    MessageText("Mano o almoço tava bom demais", 107, 1737006513000, 202),
    MessageText("O meu também kkk", 108, 1737006578000, 101),

    MessageText("Bora continuar", 109, 1737006639000, 202),
    MessageText("Partiu", 110, 1737006692000, 101),

    MessageText("Mais tarde te chamo", 111, 1737006750000, 202),
    MessageText("Beleza", 112, 1737006804000, 101),

    MessageText("Qualquer coisa manda msg", 113, 1737006857000, 202),
    MessageText("Pode deixar", 114, 1737006900000, 101),

    MessageText("Tô indo ali resolver um trem", 115, 1737006964000, 202),
    MessageText("Fechou", 116, 1737007028000, 101),

    MessageText("Volto depois", 117, 1737007089000, 202),
    MessageText("Tranquilo", 118, 1737007130000, 101),

    MessageText("Valeuu", 119, 1737007179000, 202),
    MessageText("Tmj!", 120, 1737007230000, 101) */
)
