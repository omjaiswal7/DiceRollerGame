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
            PlayersNameScreen(navController)
        }

        composable<DiceRoutes.DiceGame> { backStackEntry ->
            val args = backStackEntry.toRoute<DiceRoutes.DiceGame>()
            DiceGameScreen(
                player01Name = args.player01,
                player02Name = args.player02,
                targetScore = args.targetScore,
                navController = navController
            )
        }

        composable<DiceRoutes.Winner> { backStackEntry ->
            val args = backStackEntry.toRoute<DiceRoutes.Winner>()
            WinnerScreen(
                winnerName = args.winnerName,
                navController = navController
            )
        }
    }

}