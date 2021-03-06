package com.example.restaurent

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.restaurent.screen.*

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ScreenNavigate.SplashScreen.route
    ){
        composable(
            route = ScreenNavigate.LoginScreen.route
        ){
            LoginScreen(navController= navController)
        }

        composable(
            route = ScreenNavigate.SplashScreen.route
        ){
            SplashScreen(navController= navController)
        }

        composable(
            route = ScreenNavigate.MainScreen1.route
        ){
            MainScreen()
        }

        composable(
            route = ScreenNavigate.RegisterScreen.route
        ){
            RegistrationScreen(navController= navController)
        }
        composable(
            route = ScreenNavigate.OrderPlaceScreen.route
        ){
            OrderPlace(navController= navController)
        }

    }

}
