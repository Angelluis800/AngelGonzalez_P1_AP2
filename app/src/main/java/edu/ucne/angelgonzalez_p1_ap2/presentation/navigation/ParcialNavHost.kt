package edu.ucne.angelgonzalez_p1_ap2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.angelgonzalez_p1_ap2.presentation.entidad.SistemaListScreen

@Composable

fun ParcialNavHost(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.SistemaList
    ) {
        composable<Screen.SistemaList> {
            SistemaListScreen(
                createSistema = {
                    navHostController.navigate(Screen.Sistema(0))
                },
                goToMenu = {
                    navHostController.navigateUp()
                },
                goToSistema = { sistemaId ->
                    navHostController.navigate(Screen.Sistema(sistemaId = sistemaId))
                }
            )
        }
    }
}