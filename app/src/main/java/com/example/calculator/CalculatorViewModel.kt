package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {

    companion object {
        private const val MAX_NUM_LENGTH = 8
        private const val MAX_STRING_LENGTH = 24
    }

    var state by mutableStateOf(CalculatorState())

    fun onAction(action: CalculatorAction){
        when(action){
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.BackSpace-> backspace()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Calculate -> calculate()
        }
    }

    private fun calculate() {
        if (containsIllegalDivision(state.mathString)){
            state = state.copy(
                resultString = "Can't divide by 0."
            )
            return
        }

        if (!state.isLastInputOperation && !state.mathString.last().equals(".")) {
            state = state.copy(
                resultString = calculation()
            )
        }
        return
    }

    private fun enterDecimal() {
        if (!state.lastNumber.contains(".") && state.isLastInputNumber && state.lastNumber.length < MAX_NUM_LENGTH && state.mathString.length < MAX_STRING_LENGTH) {
            state = state.copy(
                mathString = state.mathString + ".",
                lastNumber = state.lastNumber + ".",

                isLastInputNumber = true,
                isLastInputOperation = false
            )
            return
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.isLastInputNumber && !state.mathString.last().equals(".") && state.mathString.length < MAX_STRING_LENGTH) {
            state = state.copy(
                mathString = state.mathString + operation.symbol,
                lastOperation = operation,

                isLastInputNumber = false,
                isLastInputOperation = true
            )
        }
    }

    private fun backspace() {
        when{
            state.isLastInputNumber && state.lastNumber.length > 1 -> state = state.copy(
                mathString = state.mathString.dropLast(1),
                lastNumber = state.lastNumber.dropLast(1),

                isLastInputNumber = true,
                isLastInputOperation = false
            )
            state.isLastInputNumber && state.lastNumber.length == 1 && state.mathString.length > 1 -> state = state.copy(
                mathString = state.mathString.dropLast(1),
                lastNumber = "",

                isLastInputNumber = false,
                isLastInputOperation = true
            )
            state.isLastInputNumber && state.lastNumber.length == 1 -> state = state.copy(
                mathString = "",
                lastNumber = "",
                lastOperation = null,

                isLastInputNumber = false,
                isLastInputOperation = false
            )
            state.isLastInputOperation && state.lastNumber == "" -> state = state.copy(
                mathString = state.mathString.dropLast(1),
                lastNumber = extractLastNumber(state.mathString),
                lastOperation = null,

                isLastInputNumber = true,
                isLastInputOperation = false
            )
            state.isLastInputOperation -> state = state.copy(
                mathString = state.mathString.dropLast(1),
                lastOperation = null,

                isLastInputNumber = true,
                isLastInputOperation = false
            )
        }
    }

    private fun enterNumber(number: Int) {
        if (state.mathString.length < MAX_STRING_LENGTH) {
            if (state.mathString.isEmpty()) {
                state = state.copy(
                    mathString = number.toString(),
                    lastNumber = number.toString(),

                    isLastInputNumber = true,
                    isLastInputOperation = false
                )
                return
            }

            if (state.isLastInputOperation) {
                state = state.copy(
                    mathString = state.mathString + number,
                    lastNumber = number.toString(),

                    isLastInputNumber = true,
                    isLastInputOperation = false
                )
                return
            }

            if (state.isLastInputNumber) {
                if (state.lastNumber.length >= MAX_NUM_LENGTH || isZero(state.lastNumber)){
                    return
                }
                state = state.copy(
                    mathString = state.mathString + number,
                    lastNumber = state.lastNumber + number,

                    isLastInputNumber = true,
                    isLastInputOperation = false
                )
            }
        }
        return
    }

    private fun extractLastNumber(mathString: String): String {
        // Define the pattern to match the last number
        val pattern = Regex("[0-9.]+(?=[^0-9.]*$)")

        // Find the match in the mathString
        val matchResult = pattern.find(mathString)

        return matchResult?.value ?: ""
    }

    private fun calculation(): String {
        val digitsOperators = digitsOperators()
        if (digitsOperators.isEmpty()){
            return ""
        }

        val multiplicationDivision = multiplicationDivisionCalculate(digitsOperators)
        if (multiplicationDivision.isEmpty()){
            return ""
        }
        
        val result = addSubtractCalculate(multiplicationDivision)
        return result.toString()
    }

    private fun addSubtractCalculate(passedList: MutableList<Any>): Float {
        var result = passedList[0] as Float

        for (i in passedList.indices){
            if (passedList[i] is Char){
                val operator = passedList[i]
                val nextNumber = passedList[i + 1] as Float
                if (operator == '+')
                    result += nextNumber
                if (operator == '-')
                    result -= nextNumber
            }
        }

        return result
    }

    private fun digitsOperators(): MutableList<Any>{
        val list = mutableListOf<Any>()
        var currentNumber = ""

        for (character in state.mathString){
            if(character.isDigit() || character == '.'){
                currentNumber += character
            }
            else{
                list.add(currentNumber.toFloat())
                currentNumber = ""
                list.add(character)
            }
        }

        list.add(currentNumber.toFloat())
        return list
    }

    private fun multiplicationDivisionCalculate(digitsOp: MutableList<Any>): MutableList<Any> {
        var list = digitsOp

        while (list.contains('x') || list.contains('/')){
            list = calcMultDiv(list)
        }

        return list
    }

    private fun calcMultDiv(passedList: MutableList<Any>): MutableList<Any> {
        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for(i in passedList.indices){
            if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex){
                val operator = passedList[i]
                val nextNumber = passedList[i+1] as Float
                val prevNumber = passedList[i-1] as Float

                when(operator){
                    'x' -> {
                        newList.add(prevNumber * nextNumber)
                        restartIndex = i + 1
                    }
                    '/' -> {
                        newList.add(prevNumber / nextNumber)
                        restartIndex = i + 1
                    }
                    else -> {
                        newList.add(prevNumber)
                        newList.add(operator)
                    }
                }
            }

            if (i> restartIndex){
                newList.add(passedList[i])
            }
        }

        return newList
    }

    private fun isZero(number: String): Boolean {
        return number == "0"
    }

    private fun containsIllegalDivision(mathStr: String): Boolean {
        val illegalDivisionRegex = Regex("/0[+\\-x/]|/0$")

        return illegalDivisionRegex.containsMatchIn(mathStr)
    }
}