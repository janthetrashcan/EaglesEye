package com.humanerrors.eagleseye

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.humanerrors.eagleseye.backend.models.Info
import com.humanerrors.eagleseye.components.HeaderSubview
import com.humanerrors.eagleseye.screens.BuildingScreen
import com.humanerrors.eagleseye.screens.OfficeScreen
import com.humanerrors.eagleseye.ui.theme.AppTheme

class ItemInfoActivity : ComponentActivity() {
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
                    
                    Box (
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        val info = intent.getParcelableExtra<Info>("info", Info::class.java)

                        when (info) {
                            is Info.BuildingInfo -> {
                                BuildingScreen(
                                    buildingInfo = info,
                                )
                            }
                            is Info.OfficeInfo -> {
                                OfficeScreen(
                                    officeInfo = info,
                                )
                            }
                            else -> { finish() }
                        }
                    }
                }
            }
        }
    }
}