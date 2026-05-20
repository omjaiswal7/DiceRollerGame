package com.example.diceroller.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class DiceRoutes {

    @Serializable
    data object PlayersName : DiceRoutes()

    @Serializable
    data class DiceGame(
        val player01: String,
        val player02: String,
        val targetScore: Int
    ) : DiceRoutes()

    @Serializable
    data class Winner(val winnerName: String) : DiceRoutes()
}