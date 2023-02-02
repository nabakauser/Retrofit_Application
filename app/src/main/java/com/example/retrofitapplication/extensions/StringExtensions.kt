package com.example.retrofitapplication.extensions

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

fun String.replaceSpaceAndHyphen(): String {
    var replacedString = ""
    for (i in this.indices) {
        replacedString += if (this[i] == ' ') '-' else if (this[i] == '-') ' ' else this[i]
    }
    return replacedString
}

fun String.toDate(dateFormat: String = "yyyy-MM-dd HH:mm:ss",
                  timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}
