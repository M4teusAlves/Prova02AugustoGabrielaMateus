package br.com.AM.prova02augustomateus

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.AM.prova02augustomateus.model.Banco_imoveis
import br.com.AM.prova02augustomateus.screens.HomeScreen
import br.com.AM.prova02augustomateus.screens.InsertScreen
import br.com.AM.prova02augustomateus.screens.ListScreen
import br.com.AM.prova02augustomateus.screens.UpdateScreen
import java.io.FileOutputStream

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    banco : Banco_imoveis,
){
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
        ){
            composable(
                route = Screen.Home.route
            ){
                HomeScreen(navController = navController, banco)
            }

            composable(
                route = Screen.Insert.route
            ){
                InsertScreen(navController = navController, banco)
            }

            composable(
                route = Screen.List.route
            ){
                ListScreen(navController = navController, banco)
            }

            composable(
                route = Screen.Update.route
            ){
                UpdateScreen(navController = navController, banco)
            }


        }
}


