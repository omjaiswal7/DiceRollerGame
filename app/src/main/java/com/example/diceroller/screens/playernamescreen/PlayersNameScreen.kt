package com.example.diceroller.screens.playernamescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.diceroller.R
import com.example.diceroller.navigation.DiceRoutes
import com.example.diceroller.ui.theme.DiceDarkBlue
import com.example.diceroller.ui.theme.DiceLightBlue


@Composable
fun PlayersNameScreen(navController: NavHostController) {

    var player01 by rememberSaveable { mutableStateOf("") }
    var player02 by rememberSaveable { mutableStateOf("") }

    var selectedScore by rememberSaveable { mutableIntStateOf(50) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            border = BorderStroke(2.dp, color = DiceLightBlue)

        ) {
            Image(
                painter = painterResource(R.drawable.dice_logo),
                contentDescription = "Dice game logo",
                modifier = Modifier
                    .size(160.dp)
                    .padding(12.dp)
            )
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Dice Roller",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = "Add player names & pick target score",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray
            )
        )

        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = player01,
//            onValueChange = { if (it.length <= 8) player01 = it },    // wrong method
            onValueChange = { if (it.length <= 8) player01 = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Player 01 Name") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = DiceDarkBlue,
                unfocusedTextColor = Color.DarkGray,
                focusedBorderColor = DiceDarkBlue,
                unfocusedBorderColor = Color.DarkGray,
                focusedLabelColor = DiceDarkBlue,
                unfocusedLabelColor = Color.DarkGray,
                cursorColor = DiceDarkBlue
            )
        )

        Spacer(Modifier.height(4.dp))

        OutlinedTextField(
            value = player02,
            onValueChange = { player02 = it.take(8) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Player 02 Name") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = DiceDarkBlue,
                unfocusedTextColor = Color.DarkGray,
                focusedBorderColor = DiceDarkBlue,
                unfocusedBorderColor = Color.DarkGray,
                focusedLabelColor = DiceDarkBlue,
                unfocusedLabelColor = Color.DarkGray,
                cursorColor = DiceDarkBlue
            )
        )

        Spacer(Modifier.height(16.dp))

        // choose target score
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(2.dp, color = DiceLightBlue)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Target Score",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color.DarkGray,
                        fontWeight = FontWeight.SemiBold,
                    )
                )

                Spacer(Modifier.height(16.dp))

                Row {
                    Button(
                        onClick = { selectedScore = 50 },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedScore == 50) DiceDarkBlue else Color.LightGray,
                            contentColor = if (selectedScore == 50) Color.White else Color.DarkGray
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 4.dp
                        ),
                        border = BorderStroke(2.dp, color = DiceLightBlue)
                    ) {
                        Text(
                            text = "50",
                            style = MaterialTheme.typography.labelLarge,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(Modifier.width(8.dp))

                    Button(
                        onClick = { selectedScore = 100 },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedScore == 100) DiceDarkBlue else Color.LightGray,
                            contentColor = if (selectedScore == 100) Color.White else Color.DarkGray
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 4.dp
                        ),
                        border = BorderStroke(2.dp, color = DiceLightBlue)
                    ) {
                        Text(
                            text = "100",
                            style = MaterialTheme.typography.labelLarge,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate(
                    DiceRoutes.DiceGame(
                        player01 = player01,
                        player02 = player02,
                        targetScore = selectedScore
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(48.dp),
            enabled = player01.isNotBlank() && player02.isNotBlank() && player01 != player02,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DiceDarkBlue,
                contentColor = Color.White
            ),
        ) {
            Text(
                text = "START GAME",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 16.sp
            )
        }


    }

}