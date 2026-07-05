package com.example.diceroller.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.diceroller.R

@Composable
fun DiceImage(
    value: Int,
    modifier: Modifier = Modifier // Added modifier parameter
) {
    val res = when (value) {
        1 -> R.drawable.dice_01
        2 -> R.drawable.dice_02
        3 -> R.drawable.dice_03
        4 -> R.drawable.dice_04
        5 -> R.drawable.dice_05
        else -> R.drawable.dice_06
    }

    Image(
        painter = painterResource(res),
        contentDescription = null,
        modifier = modifier // Apply the modifier here
    )
}