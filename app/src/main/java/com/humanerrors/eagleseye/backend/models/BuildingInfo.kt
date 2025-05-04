package com.humanerrors.eagleseye.backend.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.humanerrors.eagleseye.R
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BuildingInfo(
    val id: Int,
    val title: String = "Placeholder",
    @StringRes val description: Int = R.string.lorem,
    @DrawableRes val imageSrc: Int = R.drawable.home_carousel_image_1,
) : Parcelable
