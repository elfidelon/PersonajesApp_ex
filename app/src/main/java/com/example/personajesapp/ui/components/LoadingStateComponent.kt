package com.example.personajesapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.personajesapp.R
import com.example.personajesapp.ui.theme.ArenaOnDarkMuted
import com.example.personajesapp.ui.theme.ArenaPrimary

/**
 * Estado de carga reutilizable. En este proyecto los datos son locales
 * (repositorio en memoria) por lo que normalmente se resuelven al instante;
 * este componente queda preparado para el día en que [com.example.personajesapp.data.PersonajeRepository]
 * se reemplace por una fuente asíncrona (red o base de datos).
 */
@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = ArenaPrimary)
        Text(
            text = stringResource(R.string.loading_label),
            style = MaterialTheme.typography.bodyMedium,
            color = ArenaOnDarkMuted,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
