package com.example.personajesapp.data

import com.example.personajesapp.R
import com.example.personajesapp.model.Categorias
import com.example.personajesapp.model.Personaje

object PersonajeRepository {

    val personajes: List<Personaje> = listOf(

        Personaje(
            id = 2,
            nombre = "Charmander",
            categoria = Categorias.ADC,
            descripcion = "Veloz y ofensivo a distancia. Lanza ráfagas de fuego que " +
                "debilitan a los enemigos antes de que puedan acercarse.",
            imagenPrincipal = R.drawable.charmander,
            imagenes = listOf(R.drawable.charmander),
            modelo3D = "models/charmander.glb",
            telefono = "+52 477 100 10 02",
            paginaWeb = "https://personajesarena.example.com/charmander",
            ubicacion = "19.4326,-99.1332" // Ciudad de México
        ),

        Personaje(
            id = 4,
            nombre = "Ekans",
            categoria = Categorias.ASSASSIN,
            descripcion = "Se desliza en silencio entre las sombras, golpeando con " +
                "precisión letal a los objetivos ya debilitados.",
            imagenPrincipal = R.drawable.ekans,
            imagenes = listOf(R.drawable.ekans),
            modelo3D = "models/ekans.glb",
            telefono = "+52 477 100 10 04",
            paginaWeb = "https://personajesarena.example.com/ekans",
            ubicacion = "25.6866,-100.3161" // Monterrey
        ),
        Personaje(
            id = 5,
            nombre = "Diglett",
            categoria = Categorias.ASSASSIN,
            descripcion = "Excava bajo tierra para emboscar por sorpresa, desapareciendo " +
                "antes de que el rival pueda reaccionar.",
            imagenPrincipal = R.drawable.diglett,
            imagenes = listOf(R.drawable.diglett),
            modelo3D = "",
            telefono = "+52 477 100 10 05",
            paginaWeb = "https://personajesarena.example.com/diglett",
            ubicacion = "20.5888,-100.3899" // Querétaro
        ),
        Personaje(
            id = 6,
            nombre = "Paras",
            categoria = Categorias.SUPPORT,
            descripcion = "Libera esporas curativas que restauran la energía de sus " +
                "aliados durante los enfrentamientos prolongados.",
            imagenPrincipal = R.drawable.paras,
            imagenes = listOf(R.drawable.paras),
            modelo3D = "models/paras.glb",
            telefono = "+52 477 100 10 06",
            paginaWeb = "https://personajesarena.example.com/paras",
            ubicacion = "19.0414,-98.2063" // Puebla
        ),

        Personaje(
            id = 8,
            nombre = "Caterpie",
            categoria = Categorias.ADC,
            descripcion = "Ágil hostigador a distancia. Golpea repetidamente para " +
                "desgastar al enemigo desde una posición segura.",
            imagenPrincipal = R.drawable.caterpie,
            imagenes = listOf(R.drawable.caterpie),
            modelo3D = "models/caterpie.glb",
            telefono = "+52 477 100 10 08",
            paginaWeb = "https://personajesarena.example.com/caterpie",
            ubicacion = "32.5149,-117.0382" // Tijuana
        ),
        Personaje(
            id = 9,
            nombre = "Macha",
            categoria = Categorias.TANK,
            descripcion = "Una fuerza imparable en primera línea. Absorbe el daño de " +
                "su equipo mientras abre brechas en la defensa rival.",
            imagenPrincipal = R.drawable.macha,
            imagenes = listOf(R.drawable.macha),
            modelo3D = "models/macha.glb",
            telefono = "+52 477 100 10 09",
            paginaWeb = "https://personajesarena.example.com/macha",
            ubicacion = "21.1619,-86.8515" // Cancún
        ),
        Personaje(
            id = 1,
            nombre = "Bulbasaur",
            categoria = Categorias.TANK,
            descripcion = "Un guardián de la naturaleza. Su caparazón resiste los golpes " +
                    "más duros mientras protege a sus aliados con enredaderas defensivas.",
            imagenPrincipal = R.drawable.bulbasaur,
            imagenes = listOf(R.drawable.bulbasaur),
            modelo3D = "",
            telefono = "+52 477 100 10 01",
            paginaWeb = "https://personajesarena.example.com/bulbasaur",
            ubicacion = "21.1250,-101.6860" // León, Guanajuato
        ),
        Personaje(
            id = 3,
            nombre = "Pikachu",
            categoria = Categorias.MAGE,
            descripcion = "Canalizador de energía eléctrica. Sus descargas atraviesan " +
                    "formaciones enemigas y potencian a todo su equipo.",
            imagenPrincipal = R.drawable.pikachu,
            imagenes = listOf(R.drawable.pikachu),
            modelo3D = "",
            telefono = "+52 477 100 10 03",
            paginaWeb = "https://personajesarena.example.com/pikachu",
            ubicacion = "20.6597,-103.3496" // Guadalajara
        ),
        Personaje(
            id = 7,
            nombre = "Oddish",
            categoria = Categorias.SUPPORT,
            descripcion = "Controla el campo de batalla con esporas que ralentizan y " +
                    "debilitan a los rivales, protegiendo a su retaguardia.",
            imagenPrincipal = R.drawable.oddish,
            imagenes = listOf(R.drawable.oddish),
            modelo3D = "models/oddish.glb",
            telefono = "+52 477 100 10 07",
            paginaWeb = "https://personajesarena.example.com/oddish",
            ubicacion = "20.9674,-89.5926" // Mérida
        ),
    )

    fun obtenerPorId(id: Int): Personaje? = personajes.find { it.id == id }
}
