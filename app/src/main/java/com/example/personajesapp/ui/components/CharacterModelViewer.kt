package com.example.personajesapp.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.personajesapp.R
import com.example.personajesapp.ui.theme.ArenaOnDarkMuted
import com.example.personajesapp.ui.theme.ArenaPrimary
import com.example.personajesapp.ui.theme.ArenaSecondary
import com.example.personajesapp.ui.theme.ArenaSurfaceVariantDark
import com.google.android.filament.LightManager
import io.github.sceneview.Scene
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.rememberCameraManipulator
import io.github.sceneview.rememberCameraNode
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberEnvironment
import io.github.sceneview.rememberEnvironmentLoader
import io.github.sceneview.rememberModelInstance
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.utils.intervalSeconds

/**
 * CharacterModelViewer — Renderiza el modelo 3D con fondo transparente.
 * Se ha eliminado el suelo blanco para una integración limpia.
 */
@Composable
fun CharacterModelViewer(
    modeloPath: String,
    modifier: Modifier = Modifier,
    categoria: String = ""
) {
    val hayModelo = modeloPath.isNotBlank()
    val colorAcento = if (categoria.isNotBlank()) colorParaCategoria(categoria) else ArenaSurfaceVariantDark

    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.radialGradient(
                    listOf(
                        colorAcento.copy(alpha = 0.35f),
                        ArenaSurfaceVariantDark
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        if (hayModelo) {
            Real3DModel(
                modeloPath = modeloPath,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            ModelPlaceholder(hayModelo = false, modeloPath = modeloPath)
        }
    }
}

@Composable
private fun Real3DModel(modeloPath: String, modifier: Modifier = Modifier) {
    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine)
    val environmentLoader = rememberEnvironmentLoader(engine)
    
    val cameraNode = rememberCameraNode(engine) {
        position = Position(z = 2f) // Acercado de 3.5f a 2.8f para ver al personaje más cerca
    }
    val cameraManipulator = rememberCameraManipulator(orbitHomePosition = cameraNode.worldPosition)
    
    var rotationY by remember { mutableStateOf(0f) }
    var lastFrameTimeNanos by remember { mutableStateOf<Long?>(null) }

    // Forzamos environment transparente (isOpaque = false) para quitar "lo blanco"
    val environment = rememberEnvironment(environmentLoader, isOpaque = false)

    Scene(
        modifier = modifier,
        engine = engine,
        modelLoader = modelLoader,
        environmentLoader = environmentLoader,
        cameraNode = cameraNode,
        cameraManipulator = cameraManipulator,
        environment = environment,
        isOpaque = false, // Escena transparente
        onFrame = { frameTimeNanos ->
            val deltaSeconds = frameTimeNanos.intervalSeconds(lastFrameTimeNanos)
            lastFrameTimeNanos = frameTimeNanos
            rotationY += (deltaSeconds * 15.0).toFloat()
        }
    ) {
        // Iluminación optimizada
        LightNode(
            type = LightManager.Type.DIRECTIONAL,
            apply = {
                intensity(60_000f)
                direction(0f, -1f, -1f)
            }
        )

        LightNode(
            type = LightManager.Type.DIRECTIONAL,
            apply = {
                intensity(20_000f)
                direction(1f, 1f, 1f)
            }
        )

        // Modelo 3D
        rememberModelInstance(modelLoader, modeloPath)?.let { instance ->
            ModelNode(
                modelInstance = instance,
                rotation = Rotation(y = rotationY),
                scaleToUnits = 1.5f, // Aumentado el tamaño del modelo de 1.2f a 1.5f
                centerOrigin = Position(y = -1.0f)
            )
        }
    }
}

@Composable
private fun ModelPlaceholder(hayModelo: Boolean, modeloPath: String) {
    val infiniteTransition = rememberInfiniteTransition(label = "modelPlaceholder")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.ViewInAr,
            contentDescription = null,
            tint = if (hayModelo) ArenaSecondary else ArenaOnDarkMuted,
            modifier = Modifier
                .rotate(rotation)
                .padding(bottom = 12.dp)
        )
        Text(
            text = "VISTA 3D",
            style = MaterialTheme.typography.titleMedium,
            color = ArenaOnDarkMuted,
            textAlign = TextAlign.Center
        )
    }
}
