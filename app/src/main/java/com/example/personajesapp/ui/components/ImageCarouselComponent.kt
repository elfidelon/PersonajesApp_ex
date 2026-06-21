package com.example.personajesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.personajesapp.R
import com.example.personajesapp.ui.theme.ArenaOnDarkMuted
import com.example.personajesapp.ui.theme.ArenaPrimary

/**
 * Carrusel horizontal de imágenes/skins del personaje, basado en
 * [HorizontalPager] (la implementación moderna recomendada por Compose,
 * incluida directamente en `androidx.compose.foundation`, sin dependencias
 * extra). Si en el futuro agregas más de una imagen por personaje en
 * `Personaje.imagenes`, el indicador de página y el swipe funcionan
 * automáticamente sin cambios adicionales.
 */
@Composable
fun ImageCarousel(
    imagenes: List<Int>,
    contentDescriptionBase: String,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { imagenes.size })

    Box(modifier = modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(id = imagenes[page]),
                contentDescription = "${stringResource(R.string.content_description_character_image)} $contentDescriptionBase",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        if (imagenes.size > 1) {
            PageIndicator(
                pagerState = pagerState,
                pageCount = imagenes.size,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp)
            )
        }
    }
}

@Composable
private fun PageIndicator(
    pagerState: PagerState,
    pageCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { index ->
            val seleccionado = pagerState.currentPage == index
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(if (seleccionado) 9.dp else 7.dp)
                    .clip(CircleShape)
                    .background(if (seleccionado) ArenaPrimary else ArenaOnDarkMuted.copy(alpha = 0.4f))
            )
        }
    }
}
