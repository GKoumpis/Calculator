package com.example.calculator

data class CalculatorState (
    val mathString: String = "",

    val lastNumber: String = "",
    val lastOperation: CalculatorOperation? = null,

    val isLastInputNumber: Boolean = false,
    val isLastInputOperation: Boolean = false,

    val resultString: String = ""
)