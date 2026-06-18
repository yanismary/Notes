package com.neac.userapp.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyTextField(
    name : String,
    onNameChange : (String) -> Unit
) {
    TextField(
        value = name,
        onValueChange = onNameChange,
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        textStyle = TextStyle(
            fontSize = 25.sp
        )
    )
}