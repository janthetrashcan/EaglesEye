package com.humanerrors.eagleseye.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CarouselItem(
    val id: Int,
    @DrawableRes val imageResId: Int,
    @StringRes val contentDescriptionResId: Int
)
