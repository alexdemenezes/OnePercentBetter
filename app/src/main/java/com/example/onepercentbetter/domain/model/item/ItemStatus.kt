package com.example.onepercentbetter.domain.model.item

enum class ItemStatus(val description: String) {
    LEARNED("Aprendeu"),
    IMPROVED(description = "Aprimorou");

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