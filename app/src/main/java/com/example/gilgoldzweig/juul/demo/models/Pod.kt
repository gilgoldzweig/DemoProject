package com.example.gilgoldzweig.juul.demo.models

import android.os.Parcel
import android.os.Parcelable
import com.example.gilgoldzweig.juul.demo.R
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import goldzweigapps.com.annotations.annotations.GencyclerModel
import goldzweigapps.com.annotations.annotations.GencyclerViewHolder
import goldzweigapps.com.core.preferences.preferences


@GencyclerViewHolder(R.layout.item_product) //Gencycler(My library) annotation
data class Pod(
    @JsonProperty("id")
    var id: String = "",

    @JsonProperty("price")
    var price: Int = 0,

    @JsonProperty("image_url")
    var imageUrl: String = "",

    @JsonProperty("name")
    var name: String = "",

    @JsonProperty("description")
    var description: String = "",

    @JsonProperty("thumbnail_url")
    var thumbnailUrl: String = "") : Parcelable, GencyclerModel {

    @get:JsonIgnore
    @set:JsonIgnore
    //SharedPreferences delegate I'm using it here because it's not a lot of info
    //if I would had a bit more time I would implement it properly with ROOM ORM as database
    var favorite: Boolean by preferences(false, "favorite_$id")

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeString(id)
            writeInt(price)
            writeString(imageUrl)
            writeString(name)
            writeString(description)
            writeString(thumbnailUrl)
        }
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Pod> {
        override fun createFromParcel(parcel: Parcel) = Pod(parcel)

        override fun newArray(size: Int) = arrayOfNulls<Pod>(size)
    }
}