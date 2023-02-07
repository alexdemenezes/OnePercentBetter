package com.example.onepercentbetter.domain.model.item

enum class ItemStatus {
    LEARNED,
    IMPROVED;

    companion object {
        fun getStatusByName(name: String): ItemStatus {
            for (status in values()) {
                if (status.name == name.uppercase()) {
                    return status
                }
            }
            return LEARNED
        }
    }
}