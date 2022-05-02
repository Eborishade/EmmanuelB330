package com.example.worddictionary.activities

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.worddictionary.R
import com.example.worddictionary.activities.words.WordDictAdapter
import com.example.worddictionary.database.Word


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Word>?) {
    val adapter = recyclerView.adapter as WordDictAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageName")
fun bindImage(imgView: ImageView, imgName: String?) {
    // The imgName parameter will be auto-substituted in the imageUrl string
    val imageUrlString = "https://www.merriam-webster.com/assets/mw/static/art/dict/$imgName.gif"
    if (null != imgName) {
        val imgUri = imageUrlString.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_connection_error)
            )
            .into(imgView)
    } else {
        Glide.with(imgView.context).load(R.drawable.ic_broken_image).into(imgView)
    }
}