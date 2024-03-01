package com.example.calculator

sealed class NavBar (
    val route: String,
    val label: String
    ) {
    object Calculator : NavBar(
        route = "calc",
        label = "Calculator"
    )
    object Converter : NavBar(
        route = "converter",
        label = "Currency Converter"
    )
}
