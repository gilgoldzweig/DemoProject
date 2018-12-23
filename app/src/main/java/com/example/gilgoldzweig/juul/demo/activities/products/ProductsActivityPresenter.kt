package com.example.gilgoldzweig.juul.demo.activities.products

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.example.gilgoldzweig.juul.demo.application.JuulApplication
import com.example.gilgoldzweig.juul.demo.base.BasePresenter
import com.example.gilgoldzweig.juul.demo.extensions.hasInternetConnection
import com.example.gilgoldzweig.juul.demo.models.Pod
import com.example.gilgoldzweig.juul.demo.modules.network.retrofit.ProductsRetrofitService
import com.example.gilgoldzweig.movies.extensions.Timber
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductsActivityPresenter(private val activity: ProductsActivity) : BasePresenter<ProductsActivityPresenterCallback> {

    override var callback: ProductsActivityPresenterCallback? = activity

    @Inject
    lateinit var productsRetrofitService: ProductsRetrofitService

    private val compositeDisposable = CompositeDisposable()

    private val networkResponseLiveData = MutableLiveData<List<Pod>>()


    init {
        JuulApplication.networkComponent.inject(this)
        activity.lifecycle.addObserver(this) //Observing lifecycle state
        networkResponseLiveData.observe(activity, Observer {
            if (it == null) return@Observer
            callback?.onProductsUpdated(it)
        })
    }

    fun loadProducts() {
        val internetConnectionDisposable = hasInternetConnection()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                val productsDisposable = productsRetrofitService.getProducts()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        //Using LiveData because I want the user to receive the data
                        //even if he exited the app and returns
                        networkResponseLiveData.postValue(it.pods)
                    }, {
                        Timber.e(it)
                    })

                compositeDisposable.add(productsDisposable)
            }, {
                callback?.onDeviceNotConnectedToInternet()
            })
        compositeDisposable.add(internetConnectionDisposable)
    }

    /**
     * called when a product is clicked
     * Goes through  here because it makes it easier to test the ui
     */
    fun selectProduct(product: Pod) {
        callback?.onProductSelected(product)
    }

    /**
     * called when a filter type is changed
     * Goes through  here because it makes it easier to test the ui
     */
    fun filterItems(favoritesOnly: Boolean) {
        val products = networkResponseLiveData.value ?: return // if it's null it means that no items exist

        if (favoritesOnly) { //Could be shortened statement but it didn't seem very readable
            callback?.onProductsUpdated(products.filter { it.favorite })
        } else {
            callback?.onProductsUpdated(products)
        }
    }


    override fun onStart() {
        super.onStart()
        //Activity is on the screen we can update the ui
        callback = activity
    }

    override fun onStop() {
        super.onStop()
        //Activity is not on the screen we can't update the ui
        callback = null
    }

    override fun onDestroy() {
        super.onDestroy()
        callback = null

        //clearing all disposables
        compositeDisposable.dispose()
    }


}