package com.humanerrors.eagleseye.backend.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.humanerrors.eagleseye.R

@Composable
fun buildingItems () = listOf (
    Info.BuildingInfo (
        id = 0,
        title = stringResource(R.string.building_0_title),
        description = R.string.building_0_description,
        imageSrc = R.drawable.building_0,
        offices = officeItems().filter { it.buildingId == 0 }
    ),
    Info.BuildingInfo (
        id = 1,
        title = stringResource(R.string.building_1_title),
        description = R.string.building_1_description,
        imageSrc = R.drawable.building_1,
        offices = officeItems().filter { it.buildingId == 1 }
    ),
    Info.BuildingInfo (
        id = 2,
        title = stringResource(R.string.building_2_title),
        description = R.string.building_2_description,
        imageSrc = R.drawable.building_2,
        offices = officeItems().filter { it.buildingId == 2 }
    )
)

@Composable
fun officeItems () = listOf (
    Info.OfficeInfo (
        id = 0,
        title = stringResource(R.string.office_0_title),
        description = R.string.office_0_description,
        // imageSrc = R.drawable.office_0,
        buildingId = 0
    ),
    Info.OfficeInfo (
        id = 1,
        title = stringResource(R.string.office_1_title),
        description = R.string.office_1_description,
        // imageSrc = R.drawable.office_1,
        buildingId = 0
    ),
    Info.OfficeInfo (
        id = 2,
        title = stringResource(R.string.office_2_title),
        description = R.string.office_2_description,
        // imageSrc = R.drawable.office_2,
        buildingId = 0
    ),
)