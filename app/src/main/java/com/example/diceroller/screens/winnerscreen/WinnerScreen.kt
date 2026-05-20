package com.example.diceroller.screens.winnerscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.diceroller.R
import com.example.diceroller.navigation.DiceRoutes
import com.example.diceroller.ui.theme.DiceDarkBlue
import com.example.diceroller.ui.theme.DiceLightBlue

@Composable
fun WinnerScreen(winnerName: String, navController: NavHostController) {

    Column(
       modifier = Modifier
           .fillMaxSize()
           .padding(24.dp)
           .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(0.75f).padding(top = 24.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = DiceDarkBlue,
                contentColor = Color.White
            ),
            border = BorderStroke(2.dp, color = DiceLightBlue)
        ) {
            Text(
                text = "WINNER",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp,vertical = 12.dp),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    textAlign = TextAlign.Center
                )
            )
        }

        Spacer(Modifier.height(32.dp))

        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            border = BorderStroke(2.dp, color = DiceLightBlue)
        ) {
            Image(
                painter = painterResource(R.drawable.winner_trophy),
                contentDescription = "Winner trophy",
                modifier = Modifier
                    .size(250.dp)
                    .padding(16.dp)
            )
        }

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Congratulations!",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "$winnerName won the game!",
            style = MaterialTheme.typography.titleSmall.copy(
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            )
        )

        Spacer(Modifier.height(40.dp))

        Button(
            onClick = { navController.navigate(DiceRoutes.PlayersName){
                // pay attention here
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            } },
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(bottom = 32.dp)
                .height(48.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DiceDarkBlue,
                contentColor = Color.White
            ),
            border = BorderStroke(2.dp, color = DiceLightBlue)
        ) {
            Text(
                text = "START NEW GAME",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 16.sp
            )
        }
    }

}