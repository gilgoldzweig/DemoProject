package com.example.gilgoldzweig.juul.demo.activities.products

import com.example.gilgoldzweig.juul.demo.base.BasePresenterCallBack
import com.example.gilgoldzweig.juul.demo.models.Pod

interface ProductsActivityPresenterCallback : BasePresenterCallBack {

    fun onDeviceNotConnectedToInternet()
    fun onProductSelected(product: Pod)
    fun onProductsUpdated(products: List<Pod>)
}