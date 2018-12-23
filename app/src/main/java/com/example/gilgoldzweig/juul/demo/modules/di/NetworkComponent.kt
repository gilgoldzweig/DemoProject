package com.example.gilgoldzweig.juul.demo.modules.di

import com.example.gilgoldzweig.juul.demo.activities.details.DetailsActivityPresenter
import com.example.gilgoldzweig.juul.demo.activities.products.ProductsActivityPresenter
import com.example.gilgoldzweig.juul.demo.modules.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface NetworkComponent {
    fun inject(productsActivityPresenter: ProductsActivityPresenter)
}