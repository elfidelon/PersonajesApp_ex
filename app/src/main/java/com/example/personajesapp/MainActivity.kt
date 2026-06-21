package com.example.personajesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.personajesapp.navigation.PersonajesNavGraph
import com.example.personajesapp.ui.theme.PersonajesAppTheme

/**
 * Activity única de la app (single-activity architecture). Toda la
 * navegación entre pantallas la maneja [PersonajesNavGraph] mediante
 * Navigation Compose; no se usan Activities ni Fragments adicionales.
 *
 * La orientación landscape forzada se configura en el AndroidManifest.xml
 * (`android:screenOrientation="sensorLandscape"`), no aquí.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PersonajesAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PersonajesNavGraph()
                }
            }
        }
    }
}
