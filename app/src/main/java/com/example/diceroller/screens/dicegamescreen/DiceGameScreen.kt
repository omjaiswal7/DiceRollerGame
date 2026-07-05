package com.example.diceroller.screens.dicegamescreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diceroller.ui.components.DiceImage
import com.example.diceroller.screens.dicegamescreen.DiceGameViewModel
import com.example.diceroller.ui.theme.DiceDarkBlue
import com.example.diceroller.ui.theme.DiceLightBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DiceGameScreen(
    player01Name: String,
    player02Name: String,
    targetScore: Int,
    onBackToPlayerScreen: () -> Unit,
    onGameWinner: (String) -> Unit,
    viewModel: DiceGameViewModel = viewModel()
) {

    val player01Score = viewModel.player01Score
    val player02Score = viewModel.player02Score

    val player01Turn = viewModel.player01Turn
    val diceValue = viewModel.diceValue
    val isRolling = viewModel.isRolling

    val scope = rememberCoroutineScope()

// This won't crash, but won't save the angle if you rotate the phone mid-spin
    val rotation = remember { Animatable(0f) }
    // isko rememberSaveable karne se error aarha hai,

    var animationDiceImage by remember { mutableIntStateOf(diceValue) }

    // Helper function to avoid duplicating code for P1 and P2
    val performRoll: () -> Unit = {
        // IMPORTANT: Check isRolling HERE before launching the animation
        if (!isRolling) {
            scope.launch {
//                Step 1: Lock the gate immediately
                viewModel.startRolling()

                // 2. Visual Animation
                repeat(10) {
                    animationDiceImage = (1..6).random()
                    rotation.snapTo(0f)
                    rotation.animateTo(180f, tween(50))
                    delay(40)
                }
                // 3. Business Logic, and UNLOCKS the gate
                // This adds the score and then sets isRolling back to false
                viewModel.rollDice(
                    targetScore = targetScore,
                    onGameWinner = onGameWinner,
                    player01Name = player01Name,
                    player02Name = player02Name
                )
                // 3. Sync visual with actual result
                animationDiceImage = viewModel.diceValue
            }
        }
    }


    Scaffold(
        topBar = { DiceGameTopBar(onNewGameClick = onBackToPlayerScreen) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // target score top card
            Card(
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .padding(top = 16.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.DarkGray
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                border = BorderStroke(2.dp, DiceLightBlue)
            ) {
                Text(
                    text = "Target: $targetScore",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(32.dp))

            // Score Row
            Row {
                // Player 01 score board card
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (player01Turn) DiceDarkBlue else Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = if (player01Turn) 6.dp else 4.dp
                    ),
                    border = BorderStroke(2.dp, DiceLightBlue)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = player01Name,
                            fontWeight = FontWeight.Medium,
                            color = if (player01Turn) Color.White else Color.DarkGray
                        )
                        Text(
                            text = player01Score.toString(),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = if (player01Turn) Color.White else Color.Gray
                        )
                    }
                }

                Spacer(Modifier.width(8.dp))

                // Player 02 score board card
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (!player01Turn) DiceDarkBlue else Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = if (!player01Turn) 6.dp else 4.dp
                    ),
                    border = BorderStroke(2.dp, DiceLightBlue)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = player02Name,
                            fontWeight = FontWeight.Medium,
                            color = if (!player01Turn) Color.White else Color.DarkGray
                        )
                        Text(
                            text = player02Score.toString(),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = if (!player01Turn) Color.White else Color.Gray
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            // player turn
            Text(
                text = if (player01Turn) "$player01Name's Turn" else "$player02Name's Turn",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = Color.DarkGray
            )

            Spacer(Modifier.height(32.dp))

            // Dice card: rolling dice animation
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                border = BorderStroke(2.dp, color = DiceLightBlue)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 36.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    DiceImage(
                        value = animationDiceImage,
                        modifier = Modifier
                            .size(200.dp)
                            .rotate(rotation.value) // Rotate the image directly
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Roll a 6 to keep your turn!",
                color = Color.Gray
            )

            Spacer(Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                // player01 roll button
                RollButton(
                    text = "P1: Roll",
                    isEnabled = !isRolling && player01Turn,
                    isTurn = player01Turn,
                    onClick = performRoll,
                    modifier = Modifier.weight(1f)
                )

                Spacer(Modifier.width(8.dp))

                // player02 roll button
                RollButton(
                    text = "P2: Roll",
                    isEnabled = !isRolling && !player01Turn,
                    isTurn = !player01Turn,
                    onClick = performRoll,
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }
}

@Composable
fun RollButton(
    text: String,
    isEnabled: Boolean,
    isTurn: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        shape = CircleShape,
        border = BorderStroke(2.dp, color = DiceLightBlue),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isTurn) DiceDarkBlue else MaterialTheme.colorScheme.surfaceVariant,
            contentColor = if (isTurn) Color.White else Color.Gray
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (isTurn) 4.dp else 2.dp
        )
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}