package com.example.gilgoldzweig.juul.demo.activities.products

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.gilgoldzweig.juul.demo.R
import com.example.gilgoldzweig.juul.demo.activities.details.DetailsActivity
import com.example.gilgoldzweig.juul.demo.activities.products.adapter.ProductsRecyclerAdapter
import com.example.gilgoldzweig.juul.demo.models.Pod
import goldzweigapps.com.core.views.onClick
import goldzweigapps.com.core.views.snackBar
import goldzweigapps.com.gencycler.extensions.SimpleGesturesHelper
import goldzweigapps.com.gencycler.listeners.OnItemClickedListener
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity(), ProductsActivityPresenterCallback, OnItemClickedListener<Pod> {

    private lateinit var presenter: ProductsActivityPresenter
    private lateinit var adapter: ProductsRecyclerAdapter
    private var itemsFiltered = false
    private lateinit var gesturesHelper: SimpleGesturesHelper<ProductsRecyclerAdapter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        presenter = ProductsActivityPresenter(this)
        adapter = ProductsRecyclerAdapter(this, onItemClickedListener = this)
        gesturesHelper = SimpleGesturesHelper(adapter)
        gesturesHelper.setDragAndDropEnabled(true, false)
        gesturesHelper.setSwipeEnabled(true, SimpleGesturesHelper.START, SimpleGesturesHelper.END)
        gesturesHelper.attachToRecyclerView(products_activity_rcv)
        products_activity_rcv.layoutManager = LinearLayoutManager(this)
        products_activity_rcv.adapter = adapter

        products_activity_filter_fab.onClick {
            itemsFiltered = !itemsFiltered
            presenter.filterItems(itemsFiltered)
        }
        presenter.loadProducts()
    }

    override fun onDeviceNotConnectedToInternet() {
        products_activity_root.snackBar("You don't have internet connection")
    }

    override fun onItemClicked(item: Pod, position: Int) {
        presenter.selectProduct(item)
    }

    override fun onProductSelected(product: Pod) {
        val target = Intent(this, DetailsActivity::class.java)
        target.putExtra(DetailsActivity.PRODUCT_INTENT_KEY, product)

        startActivity(target)
    }

    override fun onProductsUpdated(products: List<Pod>) {
        adapter.add(products)
        adapter.add(products)
        adapter.add(products)
        adapter.add(products)
        adapter.add(products)
        adapter.add(products)
        adapter.add(products)
        adapter.add(products)
        adapter.add(products)
        adapter.add(products)

    }
}
