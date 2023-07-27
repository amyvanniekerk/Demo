package com.example.hoods

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.testing.R
import kotlinx.coroutines.launch

enum class MultiFloatingState {
    Expanded,
    Collapsed
}

class MinFabItem(
    @DrawableRes icon: Int,
    val label: String,
)

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    var MutliFloatingState by remember {
        mutableStateOf(MultiFloatingState.Collapsed)
    }

    val coroutine = rememberCoroutineScope()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
//    val item = listOf(
//        Pair("Show barcode", R.drawable.scanner),
//        Pair("Virtual swipe", R.drawable.ss_swipe)
//    )

    val items = listOf(

        MinFabItem(
            label = "Show barcode",
            icon = R.drawable.ss_swipe,
        ),

        MinFabItem(
            label = "Virtual swipe",
            icon = R.drawable.scanner,
        ),
    )

    Scaffold(
        floatingActionButton = {
            multiFloatingButton(
                multiFloatingState = MutliFloatingState,
                onMultiFabStateChanged = { MutliFloatingState = it },
                items = items
            )
        }
    ) {

        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetShape = RoundedCornerShape(16.dp),
            sheetContent = {
                radioButtons()
            },
            sheetPeekHeight = 0.dp
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LoadingShimmerEffect()

                Text(text = "This is your modal slide up.")

                Button(
                    onClick = {
                        coroutine.launch {
                            if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            } else {
                                bottomSheetScaffoldState.bottomSheetState.collapse()
                            }
                        }
                    }) {
                    Text(text = "pop it, lock it")
                }
            }
        }
    }
}

@Composable
fun multiFloatingButton(
    multiFloatingState: MultiFloatingState,
    onMultiFabStateChanged: (MultiFloatingState) -> Unit,
    items: List<MinFabItem>
) {

    var currentState by remember { mutableStateOf(MultiFloatingState.Collapsed) }
    val context = LocalContext.current
    val transition = updateTransition(targetState = multiFloatingState, label = "transition")

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.End
    ) {

        if (transition.currentState == MultiFloatingState.Expanded) {
            items.forEach {
                MinFab(
                    label = "Boom clap",
                    icon = R.drawable.scanner,
                    onMinFabItemClick = {
                    },
                )

                MinFab(
                    label = "boom de clap de clap",
                    icon = R.drawable.ss_swipe,
                    onMinFabItemClick = {
                    },
                )
            }
        }
        FloatingActionButton(
            shape = MaterialTheme.shapes.large.copy(CornerSize(percent = 50)),
            backgroundColor = colorResource(id = R.color.purple_700),
            contentColor = Color.White,
            onClick = {
                Toast.makeText(context, "testing", Toast.LENGTH_SHORT).show()

                onMultiFabStateChanged(
                    if (transition.currentState == MultiFloatingState.Expanded) {
                        MultiFloatingState.Collapsed
                    } else {
                        MultiFloatingState.Expanded
                    }
                )
            },

            ) {
            if (currentState == MultiFloatingState.Expanded) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.rotate(45f) // Customize the rotation angle as needed
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Swipe",
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ss_swipe),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun MinFab(
    label: String,
    @DrawableRes icon: Int,
    onMinFabItemClick: (MinFabItem) -> Unit,
) {

    var isModalOpen by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .shadow(
                elevation = 12.dp,
                spotColor = Color(0x29000000),
                ambientColor = Color(0x29000000)
            )
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 24.dp))
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    )
    {
        Text(text = label)

        Image(
            painter = painterResource(id = icon),
            contentDescription = "fab",
            modifier = Modifier
                .size(40.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        bounded = false,
                        radius = 20.dp,
                        color = Color.Yellow
                    ),
                    onClick = {
                        isModalOpen = !isModalOpen
                    }
                )
        )
    }
    if (isModalOpen) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.5f))
                .clickable(
                    onClick = {
                        // Close the modal when the user clicks outside of it
                        isModalOpen = false
                    },
                ),
            contentAlignment = Alignment.Center
        ) {
            // Custom modal content
            // Replace this with your actual modal content composable
            Column(
                modifier = Modifier
                    .clickable {
                        // Close the modal when the confirm button is clicked
                        isModalOpen = false
                    }
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(text = "Modal Title")
                Text(text = "This is the modal content.")

            }
        }
    }
}


