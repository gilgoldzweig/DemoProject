package com.example.gilgoldzweig.juul.demo.base

import android.arch.lifecycle.Lifecycle

interface BasePresenterCallBack {
    fun getLifecycle(): Lifecycle //Enforces that the extending class has a lifecycle

    val name: String? //For fragments
        get() {
        return this::class.qualifiedName ?: "No name"
    }

    fun onBackPress() = Unit //For fragments
}
