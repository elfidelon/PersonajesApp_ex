package com.example.personajesapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.personajesapp.model.Personaje

/**
 * Funciones de extensión/utilidad para lanzar los Intents implícitos
 * requeridos por la pantalla de detalle. Todas verifican que exista una
 * app capaz de resolver el Intent antes de lanzarlo (usando las
 * declaraciones <queries> del Manifest) para evitar crashes si el usuario
 * no tiene una app compatible instalada (ej. un emulador sin Google Maps).
 */
object IntentUtils {

    /** 1. Intent ACTION_DIAL: abre el marcador telefónico con el número precargado. */
    fun llamar(context: Context, telefono: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telefono"))
        lanzarSiEsPosible(context, intent)
    }

    /** 2. Intent ACTION_VIEW con esquema https: abre el navegador en la página oficial. */
    fun abrirSitioWeb(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        lanzarSiEsPosible(context, intent)
    }

    /** 3. Intent ACTION_SEND: comparte la información del personaje como texto plano. */
    fun compartir(context: Context, personaje: Personaje) {
        val texto = buildString {
            append("Conoce a ${personaje.nombre}\n")
            append("Categoría: ${personaje.categoria}\n")
            append(personaje.descripcion)
            append("\n${personaje.paginaWeb}")
        }
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Personaje destacado: ${personaje.nombre}")
            putExtra(Intent.EXTRA_TEXT, texto)
        }
        val chooser = Intent.createChooser(intent, "Compartir personaje vía")
        context.startActivity(chooser)
    }

    /** 4. Intent con geo URI: abre Google Maps (o cualquier app de mapas) en la ubicación dada. */
    fun abrirUbicacion(context: Context, ubicacion: String, nombre: String) {
        val geoUri = Uri.parse("geo:$ubicacion?q=$ubicacion(${Uri.encode(nombre)})")
        val intent = Intent(Intent.ACTION_VIEW, geoUri)
        lanzarSiEsPosible(context, intent)
    }

    private fun lanzarSiEsPosible(context: Context, intent: Intent) {
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(
                context,
                "No se encontró una app para completar esta acción",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
