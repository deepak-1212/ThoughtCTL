package test.interview.thoughtctl.utils

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView

fun ShapeableImageView.glideLoader(url: String) {
    Glide.with(context).load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop().into(this).clearOnDetach()
}