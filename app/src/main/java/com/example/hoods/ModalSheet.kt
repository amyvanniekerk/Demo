package com.example.hoods

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// enum class always has to be outside the main class / can make separate file for it
enum class RadioButtonOptions {
    OPTION_1,
    OPTION_2,
    OPTION_3
}

@Composable
fun radioButtons(
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            var selectedOption by remember { mutableStateOf(RadioButtonOptions.OPTION_1) }

            Column(Modifier.padding(16.dp)) {
                Text(text = "Sort by:")
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(
                        selected = selectedOption == RadioButtonOptions.OPTION_1,
                        onClick = { selectedOption = RadioButtonOptions.OPTION_1 }
                    )
                    Text(
                        text = "Polka dot it",
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(
                        selected = selectedOption == RadioButtonOptions.OPTION_2,
                        onClick = { selectedOption = RadioButtonOptions.OPTION_2 }
                    )
                    Text(
                        text = "Country fivin'",
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(
                        selected = selectedOption == RadioButtonOptions.OPTION_3,
                        onClick = { selectedOption = RadioButtonOptions.OPTION_3 }
                    )
                    Text(
                        text = "Hip hop hip",

                        )
                }
            }
        }
    }
}
