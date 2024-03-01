package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.theme.AlmostBlack
import com.example.calculator.ui.theme.Orange

@Composable
fun CalcScreen() {
    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(8.dp)
            .padding(bottom = 48.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                text = state.mathString,
                textAlign = TextAlign.Start,
                fontSize = 35.sp,
                color = Color.White,
                lineHeight = 35.sp,
                overflow = TextOverflow.Clip
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 22.dp),
                text = state.resultString,
                textAlign = TextAlign.End,
                fontSize = 50.sp,
                color = Color.White
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(2.6f)
                        .weight(2f),
                    symbol = "AC",
                    color = AlmostBlack,
                    textColor = Orange
                ) {
                    viewModel.onAction(CalculatorAction.Clear)
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "⌫",
                    color = AlmostBlack,
                    textColor = Orange
                ) {
                    viewModel.onAction(CalculatorAction.BackSpace)
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "/",
                    color = AlmostBlack,
                    textColor = Orange
                ) {
                    viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "1",
                    color = AlmostBlack,
                    textColor = Color.White
                ) {
                    viewModel.onAction(CalculatorAction.Number(1))
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "2",
                    color = AlmostBlack,
                    textColor = Color.White
                ) {
                    viewModel.onAction(CalculatorAction.Number(2))
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "3",
                    color = AlmostBlack,
                    textColor = Color.White
                ) {
                    viewModel.onAction(CalculatorAction.Number(3))
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "x",
                    color = AlmostBlack,
                    textColor = Orange
                ) {
                    viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "4",
                    color = AlmostBlack,
                    textColor = Color.White
                ) {
                    viewModel.onAction(CalculatorAction.Number(4))
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "5",
                    color = AlmostBlack,
                    textColor = Color.White
                ) {
                    viewModel.onAction(CalculatorAction.Number(5))
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "6",
                    color = AlmostBlack,
                    textColor = Color.White
                ) {
                    viewModel.onAction(CalculatorAction.Number(6))
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "-",
                    color = AlmostBlack,
                    textColor = Orange
                ) {
                    viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "7",
                    color = AlmostBlack,
                    textColor = Color.White
                ) {
                    viewModel.onAction(CalculatorAction.Number(7))
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "8",
                    color = AlmostBlack,
                    textColor = Color.White
                ) {
                    viewModel.onAction(CalculatorAction.Number(8))
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "9",
                    color = AlmostBlack,
                    textColor = Color.White
                ) {
                    viewModel.onAction(CalculatorAction.Number(8))
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "+",
                    color = AlmostBlack,
                    textColor = Orange
                ) {
                    viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = ".",
                    color = AlmostBlack,
                    textColor = Color.White
                ) {
                    viewModel.onAction(CalculatorAction.Decimal)
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "0",
                    color = AlmostBlack,
                    textColor = Color.White
                ) {
                    viewModel.onAction(CalculatorAction.Number(0))
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = ".",
                    color = AlmostBlack,
                    textColor = Color.White
                ) {
                    viewModel.onAction(CalculatorAction.Decimal)
                }
                CalcButton(
                    modifier = Modifier
                        .aspectRatio(1.3f)
                        .weight(1f),
                    symbol = "=",
                    color = Orange,
                    textColor = Color.White
                ) {
                    viewModel.onAction(CalculatorAction.Calculate)
                }
            }
        }
    }
}

@Composable
@Preview
fun CalcScreenPreview() {
    CalcScreen()
}