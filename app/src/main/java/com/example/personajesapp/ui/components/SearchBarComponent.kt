package com.example.personajesapp.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.personajesapp.R
import com.example.personajesapp.ui.theme.ArenaOnDarkMuted
import com.example.personajesapp.ui.theme.ArenaPrimary

/**
 * Campo de búsqueda con filtrado en tiempo real. El filtrado real ocurre
 * en [com.example.personajesapp.ui.screens.CharacterListScreen], este
 * componente solo expone el texto mediante [onQueryChange].
 */
@Composable
fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier,
        placeholder = { Text(stringResource(R.string.search_placeholder)) },
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = null, tint = ArenaOnDarkMuted)
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(Icons.Filled.Clear, contentDescription = "Limpiar búsqueda", tint = ArenaOnDarkMuted)
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ArenaPrimary,
            unfocusedBorderColor = ArenaOnDarkMuted.copy(alpha = 0.4f),
            cursorColor = ArenaPrimary
        )
    )
}
