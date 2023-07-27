package com.example.testing

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoods.CountdownTimer
import com.example.hoods.MainScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context = LocalContext.current
            Scaffold() {
                TopAppBar(
                    title = {
                        Text(text = "Welcome")

                    },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Menu, "menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.Home, "Home")
                        }
                    },
                )

                Column(
                    modifier = Modifier
                        .padding(top = 57.dp)
//                        .background(Color.Cyan)
                    ,
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(
                        text = "Hey girl",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600
                    )

                    CountdownTimer()

                    MainScreen()

                }
            }
        }
    }
}