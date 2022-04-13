package com.example.movies.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val API_KEY = "af37f4da"
const val BASE_API = "https://www.omdbapi.com"

object MovieDBClient {
    fun getClient(): MovieDBInterface {
        val requestInterceptor = Interceptor { chain ->
            val url =
                chain.request().url.newBuilder().addQueryParameter("apikey", API_KEY).build()
            val request = chain.request().newBuilder().url(url).build()
            return@Interceptor chain.proceed(request)
        }

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val okHttpClient = OkHttpClient.Builder().addInterceptor(requestInterceptor).addInterceptor(logging)
            .connectTimeout(60, TimeUnit.SECONDS).build()


        return Retrofit.Builder()
            .baseUrl(BASE_API)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieDBInterface::class.java)
    }
}