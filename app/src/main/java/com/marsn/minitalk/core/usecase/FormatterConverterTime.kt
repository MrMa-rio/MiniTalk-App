package com.marsn.minitalk.core.usecase

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import java.sql.Timestamp
import java.time.ZoneId
import kotlin.math.absoluteValue
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(FormatStringsInDatetimeFormats::class, ExperimentalTime::class)
fun LocalDateTime.formattedDateForChatLastMessage(): String {
    val nowLocalDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
    val dayUntil = nowLocalDateTime.date.daysUntil(this.date)
        .absoluteValue
    return when {
        dayUntil == 0 -> {
            this.time.format(LocalTime.Format {
                byUnicodePattern("HH:mm")
            })
        }
        dayUntil < 2 -> {
            "ontem"
        }
        dayUntil < 7 -> {

            this.date.format(LocalDate.Format {
                this.dayOfWeek(
                    names = DayOfWeekNames(
                        monday = "segunda",
                        tuesday = "terca",
                        friday = "sexta",
                        wednesday = "quarta",
                        thursday = "quinta",
                        saturday = "sábado",
                        sunday = "domingo"
                    )
                )
            })
        }
        else -> {
            this.format(LocalDateTime.Format { byUnicodePattern("dd/MM/yy") })
        }
    }
}

@OptIn(ExperimentalTime::class)
fun formatterTimestamp(timestamp: Long) : LocalDateTime {
    val localDateTime = Instant.fromEpochMilliseconds(timestamp)
        .toLocalDateTime(TimeZone.currentSystemDefault())
    return localDateTime
}