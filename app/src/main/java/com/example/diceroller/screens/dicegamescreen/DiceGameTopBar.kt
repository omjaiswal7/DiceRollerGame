package com.example.diceroller.screens.dicegamescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.diceroller.navigation.DiceRoutes
import com.example.diceroller.ui.theme.DiceDarkBlue
import com.example.diceroller.ui.theme.DiceLightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceGameTopBar(navController: NavHostController) {

    TopAppBar(
        title = {
            Text(
                text = "Dice Rolling Game",
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            Button(
                onClick = { navController.navigate(DiceRoutes.PlayersName)},
                modifier = Modifier.padding(end = 12.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = DiceDarkBlue
                ),
                border = BorderStroke(2.dp, DiceLightBlue)
            ) {
                Text(text = "New Game", fontWeight = FontWeight.Bold)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = DiceDarkBlue,
            titleContentColor = Color.White
        )
    )
}