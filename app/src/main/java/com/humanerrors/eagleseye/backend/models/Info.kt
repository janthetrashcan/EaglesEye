package com.humanerrors.eagleseye.backend.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.humanerrors.eagleseye.R
import kotlinx.parcelize.Parcelize

sealed class Info : Parcelable {
    @Parcelize
    data class BuildingInfo(
        val id: Int,
        val title: String = "Placeholder",
        @StringRes val description: Int = R.string.lorem,
        @DrawableRes val imageSrc: Int = R.drawable.placeholder,
        val offices: List<Info.OfficeInfo> = listOf (Info.OfficeInfo(id = 0))
    ) : Info()

    @Parcelize
    data class OfficeInfo(
        val id: Int,
        val title: String = "Placeholder",
        @StringRes val description: Int = R.string.lorem,
        @DrawableRes val imageSrc: Int = R.drawable.placeholder,
        val buildingId: Int = 999,
    ) : Info()
}
