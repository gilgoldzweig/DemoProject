package com.example.gilgoldzweig.juul.demo.activities.details

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.gilgoldzweig.juul.demo.R
import com.example.gilgoldzweig.juul.demo.models.Pod
import com.example.gilgoldzweig.juul.demo.ui.GlideApp
import kotlinx.android.synthetic.main.activity_details.*
import java.text.NumberFormat
import java.util.*

class DetailsActivity : AppCompatActivity(), DetailsActivityPresenterCallback {

	private val priceFormatter = NumberFormat.getCurrencyInstance(Locale.US)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_details)
		val product = intent.getParcelableExtra<Pod>(PRODUCT_INTENT_KEY)

		details_activity_toolbar.setNavigationOnClickListener {
			super.onBackPressed()
		}

		GlideApp.with(this)
				.load(product.imageUrl)
				.into(details_activity_image)

		details_activity_title_text.text = product.name
		details_activity_price_text.text = priceFormatter.format(product.price / 100.0)
		details_activity_description_text.text = product.description


	}
	companion object {
		const val PRODUCT_INTENT_KEY = "product_intent_key"
	}
}
