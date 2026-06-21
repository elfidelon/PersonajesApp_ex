package com.example.personajesapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.personajesapp.R
import com.example.personajesapp.model.Personaje
import com.example.personajesapp.ui.theme.ArenaAdcColor
import com.example.personajesapp.ui.theme.ArenaAssassinColor
import com.example.personajesapp.ui.theme.ArenaOnDark
import com.example.personajesapp.ui.theme.ArenaSecondary
import com.example.personajesapp.ui.theme.ArenaTankColor
import com.example.personajesapp.utils.IntentUtils

/**
 * Columna de "acciones rápidas" mostrada a la derecha en la pantalla de
 * detalle. Cada botón dispara uno de los 4 Intents requeridos.
 */
@Composable
fun QuickActionsColumn(
    personaje: Personaje,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        QuickActionButton(
            text = stringResource(R.string.action_call),
            icon = Icons.Filled.Call,
            containerColor = ArenaTankColor,
            onClick = { IntentUtils.llamar(context, personaje.telefono) }
        )

        QuickActionButton(
            text = stringResource(R.string.action_website),
            icon = Icons.Filled.Public,
            containerColor = ArenaSecondary,
            onClick = { IntentUtils.abrirSitioWeb(context, personaje.paginaWeb) }
        )

        QuickActionButton(
            text = stringResource(R.string.action_share),
            icon = Icons.Filled.Share,
            containerColor = ArenaAdcColor,
            onClick = { IntentUtils.compartir(context, personaje) }
        )

        QuickActionButton(
            text = stringResource(R.string.action_location),
            icon = Icons.Filled.LocationOn,
            containerColor = ArenaAssassinColor,
            onClick = { IntentUtils.abrirUbicacion(context, personaje.ubicacion, personaje.nombre) }
        )
    }
}

@Composable
private fun QuickActionButton(
    text: String,
    icon: ImageVector,
    containerColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = ArenaOnDark
        )
    ) {
        Icon(imageVector = icon, contentDescription = null)
        Text(text = text, modifier = Modifier.padding(start = 8.dp))
    }
}
