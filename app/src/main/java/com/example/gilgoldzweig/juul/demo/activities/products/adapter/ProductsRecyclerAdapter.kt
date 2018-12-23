package com.example.gilgoldzweig.juul.demo.activities.products.adapter

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import com.example.gilgoldzweig.juul.demo.R
import com.example.gilgoldzweig.juul.demo.models.Pod
import com.example.gilgoldzweig.juul.demo.ui.GlideApp
import goldzweigapps.com.annotations.annotations.Clickable
import goldzweigapps.com.annotations.annotations.Filterable
import goldzweigapps.com.annotations.annotations.GencyclerAdapter
import goldzweigapps.com.core.views.onClick
import goldzweigapps.com.gencycler.GeneratedProductsRecyclerAdapter
import goldzweigapps.com.gencycler.PodViewHolder
import goldzweigapps.com.gencycler.listeners.OnItemClickedListener
import org.jetbrains.anko.image
import java.text.NumberFormat
import java.util.*

/**
 * Generated recycler adapter class using my Gencycler library
 * Code is generated at compile time
 */
@GencyclerAdapter(Pod::class)
@Clickable
class ProductsRecyclerAdapter(context: Context,
							  products: ArrayList<Pod> = ArrayList(),
                              onItemClickedListener: OnItemClickedListener<Pod>) :
    GeneratedProductsRecyclerAdapter(context, products, onItemClickListener = onItemClickedListener) {

	private val glide = GlideApp.with(context)

	private val priceFormatter = NumberFormat.getCurrencyInstance(Locale.US)

    override fun onBindPodViewHolder(podViewHolder: PodViewHolder, pod: Pod, position: Int) {

		podViewHolder.productItemTitleText.text = pod.name
		podViewHolder.productItemPriceText.text = priceFormatter.format(pod.price / 100.0)

		setFavoriteImageResource(podViewHolder.productItemFavoriteImage, pod.favorite)

		glide.load(pod.thumbnailUrl)
				.error(R.drawable.ic_close_black_24dp)
				.into(podViewHolder.productItemThumbnailImage)

        podViewHolder.productItemFavoriteImage.onClick {
			pod.favorite = !pod.favorite //Changing the value automatically updated in shared preferences
			setFavoriteImageResource(podViewHolder.productItemFavoriteImage, pod.favorite)
		}
	}

	override fun onRecycledPodViewHolder(podViewHolder: PodViewHolder, position: Int) {
		podViewHolder.productItemTitleText.text = null
		podViewHolder.productItemPriceText.text = null
		podViewHolder.productItemThumbnailImage.image = null
		podViewHolder.productItemFavoriteImage.image = null
	}

	private fun setFavoriteImageResource(imageView: AppCompatImageView, isFavorite: Boolean) {
		if (isFavorite) {
			imageView.setImageResource(R.drawable.ic_favorite_red_900_24dp)
		} else {
			imageView.setImageResource(R.drawable.ic_favorite_border_red_900_24dp)
		}
	}
}

//Generated code below(Might change until the time i finish)


//abstract class GeneratedProductsRecyclerAdapter(val context: Context, elements: ArrayList<GencyclerDataType> = ArrayList()) : GencyclerAdapterExtensions(elements) {
//   private val inflater: LayoutInflater = LayoutInflater.from(context)
//
//   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = when(viewType) {
//           R.layout.item_product -> PodViewHolder(inflater.inflate(viewType, parent, false))
//           else -> throw IOException("unsupported type, only (Pod) are supported")
//   }
//   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//      when (holder) {
//      is PodViewHolder ->
//              holder.onBindPodViewHolder(position, elements[position] as Pod)
//
//      }
//   }
//
//   override fun getItemCount(): Int = elements.size
//
//   override fun getItemViewType(position: Int): Int {
//      val element = elements[position]
//      return when {
//
//              element is Pod -> R.layout.item_product
//              else -> throw IOException("unsupported type at $position, only (Pod) are supported")
//      }}
//
//   abstract fun PodViewHolder.onBindPodViewHolder(position: Int, element: Pod)
//
//   class PodViewHolder(view: View) : ViewHolder(view) {
//      val productItemRoot: RelativeLayout = view.findViewById(R.id.product_item_root)
//
//      val productItemThumbnailImage: AppCompatImageView = view.findViewById(R.id.product_item_thumbnail_image)
//
//      val productItemTitleText: AppCompatTextView = view.findViewById(R.id.product_item_title_text)
//
//      val productItemPriceText: AppCompatTextView = view.findViewById(R.id.product_item_price_text)
//   }
//}