package com.sagrd.clientesremoto.util.Nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sagrd.clientesremoto.ui.client.ClientesConsultScreen
import com.sagrd.clientesremoto.ui.client.ClientesFormScreen
import com.sagrd.clientesremoto.ui.ocupation.OcupacionScreen

@Composable
fun AppNavigation(context: Context,
)
{
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.ConsultScreen.route
    ) {
        //Home Screen
        composable(AppScreens.ConsultScreen.route) {
            ClientesConsultScreen(navController = navController)
        }
        composable(AppScreens.FormScreen.route) {
            ClientesFormScreen(context=context,navController = navController)
        }
        composable(AppScreens.OccupationsScreen.route){
            OcupacionScreen(navController = navController)
        }
    }
}