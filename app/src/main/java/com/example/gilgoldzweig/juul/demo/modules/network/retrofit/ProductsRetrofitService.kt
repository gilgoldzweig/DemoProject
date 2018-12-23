package com.example.gilgoldzweig.juul.demo.modules.network.retrofit

import com.example.gilgoldzweig.juul.demo.models.PodsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ProductsRetrofitService {

    @GET("products.json")
    fun getProducts(): Single<PodsResponse>
}