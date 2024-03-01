package com.example.calculator

import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("latest")
    suspend fun getExchangeRate(
        @Query("base") baseCurrency: String
    ): CurrencyResponse
}