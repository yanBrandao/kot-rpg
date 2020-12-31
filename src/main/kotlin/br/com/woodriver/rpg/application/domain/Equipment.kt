package br.com.woodriver.rpg.application.domain

data class Equipment(
    val topHead: Armour = Armour(),
    val midHead: Armour = Armour(),
    val lowHead: Armour = Armour(),
    val neck: Armour = Armour(),
    val shoulder: Armour = Armour(),
    val arms: Armour = Armour(),
    val bracelet: Armour = Armour(),
    val gloves: Armour = Armour(),
    val leftRing: Armour = Armour(),
    val rightRing: Armour = Armour(),
    val body: Armour = Armour(),
    val back: Armour = Armour(),
    val belt: Armour = Armour(),
    val pants: Armour = Armour(),
    val foot: Armour = Armour(),
    val weapon: Weapon = Weapon()
)