package com.example.worddictionary.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.converter.scalars.ScalarsConverterFactory



private const val BASE_URL = "https://www.dictionaryapi.com/api/v3/references/collegiate/json/"
private const val API_KEY = "d7be3358-8e6b-4145-9772-b82450a13696"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create()).baseUrl(BASE_URL).build()

interface WordApiService {
    @GET("{word}?key=${API_KEY}")
    suspend fun getWord(@Path("word") type: String): Response<String>
}

object DictionaryApi {
    val retrofitService : WordApiService by lazy {
        retrofit.create(WordApiService::class.java) }
}

