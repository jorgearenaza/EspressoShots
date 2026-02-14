package com.jorgearenaza.espressoshots.util

import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit

/**
 * Convierte LocalDate a milisegundos desde epoch
 */
fun LocalDate.toEpochMilli(): Long {
    return this.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

/**
 * Convierte milisegundos desde epoch a LocalDate
 */
fun Long.toLocalDate(): LocalDate {
    return java.time.Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}

/**
 * Calcula días desde una fecha (usada para "frescura" de granos)
 */
fun Long.daysAgoFromNow(): Long {
    val then = java.time.Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    val now = LocalDate.now()
    return ChronoUnit.DAYS.between(then, now)
}

/**
 * Formatea una fecha en milisegundos a string legible
 */
fun Long.toFormattedDate(): String {
    val date = toLocalDate()
    val formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return date.format(formatter)
}

/**
 * Formatea una fecha con hora
 */
fun Long.toFormattedDateTime(): String {
    val instant = java.time.Instant.ofEpochMilli(this)
    val zoned = instant.atZone(ZoneId.systemDefault())
    val formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    return zoned.format(formatter)
}
