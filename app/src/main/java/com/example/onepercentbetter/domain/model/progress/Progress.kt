package com.example.onepercentbetter.domain.model.progress

data class Progress(
    var id: String,
    var learned: Int,
    var improved: Int,
    var currentStreak: Int,
    var biggestStreak: Int
)