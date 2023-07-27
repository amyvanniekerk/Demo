package com.example.hoods

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ClickableRow(
    text: String,
    onClick: () -> Unit
) {

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(48.dp)
            .padding(start = 16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Start,
            modifier = Modifier
        )
//        Image(
//            painter = painterResource(id = image),
//            contentDescription = null,
//            modifier = Modifier
//                .padding(end = 20.dp)
//                .height(9.dp)
//                .width(18.dp)
//        )
    }
}