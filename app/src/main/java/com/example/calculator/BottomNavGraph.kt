package com.example.calculator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = NavBar.Calculator.route
    ){
        composable(route = NavBar.Calculator.route){
            CalcScreen()
        }
        composable(route = NavBar.Converter.route){
            ConverterScreen()
        }
    }
}