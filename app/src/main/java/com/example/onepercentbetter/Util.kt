package com.example.onepercentbetter

import java.text.SimpleDateFormat
import java.util.*

fun getToday(): String =
    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())