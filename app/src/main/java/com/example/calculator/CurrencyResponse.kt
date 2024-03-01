package com.example.calculator

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("base_code")
    val baseCode: String,
    @SerializedName("rates")
    val rates: Map<String, Double>
)
