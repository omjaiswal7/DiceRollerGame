package com.example.diceroller.screens.dicegamescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DiceGameViewModel : ViewModel() {

    var player01Score by mutableIntStateOf(0)
        private set

    var player02Score by mutableIntStateOf(0)
        private set

    var player01Turn by mutableStateOf((0..1).random() == 0)
        private set

    var diceValue by mutableIntStateOf(1)
        private set

    var isRolling by mutableStateOf(false)
        private set


    fun startRolling() {
        isRolling = true
    }


    // Logic for rolling the dice
    fun rollDice(
        targetScore: Int,
        onGameWinner: (String) -> Unit,
        player01Name: String,
        player02Name: String
    ) {
//        isRolling is true here, because we already called startRolling in screen

        // Note: For the animation, still handle the "fake" rolls
        // in the UI or use a side effect, but the FINAL result comes from here.
        val result = (1..6).random()
        diceValue = result

        if(player01Turn) {
            player01Score += result
            if(player01Score >= targetScore) {
                onGameWinner(player01Name)
            }
            else if(result != 6) {
                player01Turn = false
            }
        }
        else{
            player02Score += result
            if(player02Score >= targetScore) {
                onGameWinner(player02Name)
            }
            else if(result != 6) {
                player01Turn = true
            }
        }

        // 3. This UNLOCKS the buttons so the player can roll again
        isRolling = false

    }
}