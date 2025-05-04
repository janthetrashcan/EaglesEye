package com.humanerrors.eagleseye

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.humanerrors.eagleseye.backend.models.BuildingInfo
import com.humanerrors.eagleseye.components.HeaderSubview
import com.humanerrors.eagleseye.screens.BuildingScreen
import com.humanerrors.eagleseye.ui.theme.AppTheme

class BuildingInfoActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { HeaderSubview() },
                ) { innerPadding ->
                    val buildingInfo = intent.getParcelableExtra("buildingInfo", BuildingInfo::class.java) as BuildingInfo

                    BuildingScreen(
                        modifier = Modifier
                            .padding(innerPadding),
                        buildingInfo = buildingInfo
                    )
                }
            }
        }
    }
}