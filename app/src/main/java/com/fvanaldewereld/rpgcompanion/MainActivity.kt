package com.fvanaldewereld.rpgcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fvanaldewereld.rpgcompanion.common.di.COMMON_MODULE
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.data.scenario.di.SCENARIO_DATA_MODULE
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.di.SCENARIO_DOMAIN_LIB_MODULE
import com.fvanaldewereld.rpgcompanion.navigation.RPGCompanionNavigation
import com.fvanaldewereld.rpgcompanion.ui.home.di.HOME_UI_MODULE
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.di.SCENARIO_UI_DETAIL_MODULE
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.di.SCENARIO_UI_LIST_MODULE
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(COMMON_MODULE)
            modules(HOME_UI_MODULE)
            modules(SCENARIO_DOMAIN_LIB_MODULE)
            modules(SCENARIO_DATA_MODULE)
            modules(SCENARIO_UI_DETAIL_MODULE)
            modules(SCENARIO_UI_LIST_MODULE)
        }

        setContent {
            RpgCompanionTheme {
                RPGCompanionNavigation()
            }
        }
    }
}
