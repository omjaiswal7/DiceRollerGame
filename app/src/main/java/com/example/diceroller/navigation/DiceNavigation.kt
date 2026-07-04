package com.example.diceroller.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.diceroller.screens.dicegamescreen.DiceGameScreen
import com.example.diceroller.screens.playernamescreen.PlayersNameScreen
import com.example.diceroller.screens.winnerscreen.WinnerScreen

@Composable
fun DiceNavigation() {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = DiceRoutes.PlayersName
    ) {

        composable<DiceRoutes.PlayersName> {
            PlayersNameScreen(
                onStartGame = { p1, p2, score ->
                    navController.navigate(
                        DiceRoutes.DiceGame(
                            player01 = p1,
                            player02 = p2,
                            targetScore = score
                        )
                    )

                })
        }

        composable<DiceRoutes.DiceGame> { backStackEntry ->
            val args = backStackEntry.toRoute<DiceRoutes.DiceGame>()
            DiceGameScreen(
                player01Name = args.player01,
                player02Name = args.player02,
                targetScore = args.targetScore,
                onBackToPlayerScreen = {
                    navController.popBackStack(DiceRoutes.PlayersName, inclusive = false)
                },
                onGameWinner = { winner ->
                    navController.navigate(DiceRoutes.Winner(winnerName = winner))
                }
            )
        }

        composable<DiceRoutes.Winner> { backStackEntry ->
            val args = backStackEntry.toRoute<DiceRoutes.Winner>()
            WinnerScreen(
                winnerName = args.winnerName,
                onRestartGame = {
                    navController.navigate(DiceRoutes.PlayersName) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }

}