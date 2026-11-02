
package com.example.flowfund_part2

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flowfund_part2.data.preferences.SettingsRepository
import com.example.flowfund_part2.ui.settings.SettingsScreen
import com.example.flowfund_part2.ui.settings.SettingsViewModel
import com.example.flowfund_part2.ui.theme.FLOWFUNDPART2Theme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Only signed-in Firebase users may access the home screen.
        if (FirebaseAuth.getInstance().currentUser == null) {
            startActivity(
                android.content.Intent(this, LoginActivity::class.java)
            )
            finish()
            return
        }

        val repository = SettingsRepository(applicationContext)

        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>
            ): T {
                if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return SettingsViewModel(repository) as T
                }

                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }

        val settingsViewModel = ViewModelProvider(
            this,
            factory
        )[SettingsViewModel::class.java]

        setContent {
            FLOWFUNDPART2Theme {
                val settingsUiState by settingsViewModel.uiState.collectAsState()
                var showSettings by rememberSaveable {
                    mutableStateOf(false)
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    if (showSettings) {
                        SettingsScreen(
                            viewModel = settingsViewModel,
                            onBack = { showSettings = false }
                        )
                    } else {
                        HomeScreen(
                            onOpenSettings = { showSettings = true },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to FlowFund")

        Button(
            onClick = onOpenSettings,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text("Open Settings")
        }
    }
}
