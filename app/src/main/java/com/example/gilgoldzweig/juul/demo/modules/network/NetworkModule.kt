package com.example.gilgoldzweig.juul.demo.modules.network

import com.example.gilgoldzweig.juul.demo.modules.network.retrofit.ProductsRetrofitService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val baseUrl = "https://s3.us-east-2.amazonaws.com/juul-coding-challenge/"

    private val jacksonConverterFactory by lazy {
        JacksonConverterFactory.create(ObjectMapper().registerModule(KotlinModule()))
    }

    private val rxJava2CallAdapterFactory by lazy {
        RxJava2CallAdapterFactory.create()
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(jacksonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideProductsRetrofitService(): ProductsRetrofitService =
        retrofit.create(ProductsRetrofitService::class.java)

}