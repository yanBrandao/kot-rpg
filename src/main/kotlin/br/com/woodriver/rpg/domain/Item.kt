package br.com.woodriver.rpg.domain

import org.springframework.data.annotation.Id

enum class Rarity{
    COMMON, UNCOMMON, RARE, EPIC, LEGENDARY
}

enum class Position{
    HEAD, NECK, LEFT_EAR, RIGHT_EAR, NOSE, MOUTH, SHOULDER, BODY, LEFT_ARM, RIGHT_ARM, LEFT_HAND, RIGHT_HAND, FINGERS,
    WAIST, LEGS, FEET
}

class Item(@Id val key: String,
           val name: String,
           val weight: Double,
           val price: Double,
           val position: Position,
           val rarity: Rarity) {
}