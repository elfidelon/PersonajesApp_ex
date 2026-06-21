package com.example.personajesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.personajesapp.ui.theme.ArenaOnDark
import com.example.personajesapp.ui.theme.ArenaPrimary
import com.example.personajesapp.ui.theme.ArenaSecondary
import com.example.personajesapp.ui.theme.ArenaSurfaceVariantDark

/**
 * Barra superior con el logo del juego. Si cuentas con un logo propio en
 * formato imagen, reemplaza el [Icon] por:
 * Image(painter = painterResource(R.drawable.logo), contentDescription = null, modifier = Modifier.size(36.dp))
 */
@Composable
fun GameTopBar(
    modifier: Modifier = Modifier,
    title: String = "PERSONAJES ARENA"
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(ArenaSurfaceVariantDark)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    brush = Brush.linearGradient(listOf(ArenaPrimary, ArenaSecondary)),
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.SportsEsports,
                contentDescription = "Logo del juego",
                tint = ArenaOnDark
            )
        }

        Box(modifier = Modifier.padding(start = 14.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = ArenaOnDark,
                letterSpacing = 1.5.sp
            )
        }
    }
}
