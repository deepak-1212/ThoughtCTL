package test.interview.thoughtctl.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun Long.convertToDateTimeFormat(): String {
    val sdf: DateFormat = SimpleDateFormat("dd/MM/yy hh:mm a", Locale.ENGLISH)
    val netDate = Date(this * 1000)
    return sdf.format(netDate)
}