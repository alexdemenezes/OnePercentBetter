package com.example.onepercentbetter

import java.text.SimpleDateFormat
import java.util.*

fun getToday(): String =
    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

fun getDayOfWeek(): String {
    val date = Date()
    val dayOfWeek = SimpleDateFormat("EEEE", Locale("pt", "BR")).format(date)
    val dayOfMonth = SimpleDateFormat("dd").format(date)
    return "${dayOfWeek.replaceFirstChar { 
        it.uppercase()
    }}, $dayOfMonth."
}
