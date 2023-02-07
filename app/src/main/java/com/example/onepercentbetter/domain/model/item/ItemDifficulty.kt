package com.example.onepercentbetter.domain.model.item

enum class ItemDifficulty(val description: String) {
    EASY("Fácil"),
    MEDIUM("Médio"),
    HARD("Difícil");

    companion object {
        fun getDifficultyByName(name: String): ItemDifficulty {
            for (difficulty in values()) {
                if (difficulty.name == name.uppercase()) {
                    return difficulty
                }
            }
            return EASY
        }
    }
}