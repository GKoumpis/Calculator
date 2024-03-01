package com.example.calculator

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.Countries.Companion.CURRENCY_CODES_LIST
import com.example.calculator.ui.theme.AlmostBlack
import com.example.calculator.ui.theme.Orange
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ConverterScreen() {

    val fromCurrencyCode = remember { mutableStateOf("USD") }
    val toCurrencyCode = remember { mutableStateOf("EUR") }
    val amountValue = remember { mutableStateOf("") }
    val convertedAmount = remember { mutableStateOf("") }
    val maxInputChars = 10

    val context = LocalContext.current
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    var isFromSelected = true

    val retrofit = Retrofit.Builder()
        .baseUrl("https://open.er-api.com/v6/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val currencyApiService = retrofit.create(CurrencyApiService::class.java)

    BottomSheetScaffold(
        modifier = Modifier
            .padding(bottom = 56.dp),
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(CURRENCY_CODES_LIST){ item ->
                        Text(
                            text = "${item.currencyCode}\t ${item.countryName}",
                            color = Color.White,
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .clickable {
                                    if (isFromSelected) {
                                        fromCurrencyCode.value = item.currencyCode
                                    } else {
                                        toCurrencyCode.value = item.currencyCode
                                    }
                                    scope.launch { scaffoldState.bottomSheetState.collapse() }
                                }
                        )
                    }
                }
            }
                       } ,
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetElevation = 56.dp,
        sheetBackgroundColor = AlmostBlack,
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
    ) {
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
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(32.dp)
                )
                Text(
                    text = "From",
                    fontSize = 12.sp,
                    color = Color.White
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .height(60.dp)
                            .clickable {
                                isFromSelected = true
                                scope.launch {
                                    scaffoldState.bottomSheetState.expand()
                                }
                            }
                            .border(1.dp, Color.Gray, RoundedCornerShape(5.dp)),
                        contentAlignment = Alignment.CenterStart
                    ){
                        Text(
                            modifier = Modifier
                                .padding(6.dp),
                            text = fromCurrencyCode.value,
                            fontSize = 28.sp,
                            color = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(5.dp)),
                        contentAlignment = Alignment.CenterEnd
                    ){
                        OutlinedTextField(
                            value = amountValue.value,
                            onValueChange = {
                                if (it.length <= maxInputChars){
                                    val filteredText = it.filterIndexed { index, char ->
                                        char.isDigit() || (char == '.' && index == it.indexOf('.'))
                                    }
                                    amountValue.value = filteredText
                                }
                                            },
                            singleLine = true,
                            placeholder = {
                                Text(
                                    text = "0.00",
                                    color = Color.Gray,
                                    fontSize = 28.sp,
                                    textAlign = TextAlign.Right
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done,
                            ),
                            textStyle = TextStyle(
                                color = Orange,
                                fontSize = 28.sp,
                                textAlign = TextAlign.End
                            ),
                        )
                    }
                }
                Spacer (
                    modifier = Modifier
                        .padding(32.dp)
                )
                Text(
                    text = "To",
                    fontSize = 12.sp,
                    color = Color.White
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .height(60.dp)
                            .clickable {
                                isFromSelected = false
                                scope.launch {
                                    scaffoldState.bottomSheetState.expand()
                                }
                            }
                            .border(1.dp, Color.Gray, RoundedCornerShape(5.dp)),
                        contentAlignment = Alignment.CenterStart
                    ){
                        Text(
                            modifier = Modifier
                                .padding(6.dp),
                            text = toCurrencyCode.value,
                            fontSize = 28.sp,
                            color = Color.White
                        )
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = convertedAmount.value,
                        textAlign = TextAlign.End,
                        fontSize = 40.sp,
                        color = Orange,
                        lineHeight = 42.sp,
                        overflow = TextOverflow.Clip
                    )

                }
                Spacer(
                    modifier = Modifier
                        .padding(20.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Orange
                        ),
                        onClick = {
                            scope.launch {
                                try {
                                    val exchangeRateResponse = currencyApiService.getExchangeRate(
                                        baseCurrency = fromCurrencyCode.value
                                    )

                                    val amount = amountValue.value.toDoubleOrNull() ?: 0.0
                                    val convertedAmountValue = amount * exchangeRateResponse.rates[toCurrencyCode.value]!!

                                    convertedAmount.value = convertedAmountValue.toString()
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Connect to a network first.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    ){
                        Text(
                            text = "CONVERT",
                            fontSize = 28.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun ConverterScreenPreview() {
    ConverterScreen()
}
