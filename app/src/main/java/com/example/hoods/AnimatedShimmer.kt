package com.example.hoods

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

import androidx.compose.ui.unit.dp


@Composable
fun LoadingShimmerEffect() {

    //These colors will be used on the brush. The lightest color should be in the middle

    val gradient = listOf(
        Color.LightGray.copy(alpha = 0.9f), //darker grey (90% opacity)
        Color.LightGray.copy(alpha = 0.3f), //lighter grey (30% opacity)
        Color.LightGray.copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition() // animate infinite times

    val translateAnimation = transition.animateFloat( //animate the transition
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, // duration for the animation
                easing = FastOutLinearInEasing
            )
        )
    )
    val brush = Brush.linearGradient(
        colors = gradient,
        start = Offset(200f, 200f),
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )
    ShimmerGridItem(brush = brush)
}

@Composable
fun ShimmerGridItem(brush: Brush) {

    Column(
        modifier = Modifier
        .fillMaxWidth())
    {
        Column(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
//                 .background(Color.Cyan)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(text = "These are your default loading states.")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp), verticalAlignment = Alignment.Top
        ) {

            Spacer(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(brush)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Column(verticalArrangement = Arrangement.Center) {
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth(fraction = 0.5f)
                        .background(brush)
                )

                Spacer(modifier = Modifier.height(10.dp)) //creates an empty space between
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth(fraction = 0.7f)
                        .background(brush)
                )

                Spacer(modifier = Modifier.height(10.dp)) //creates an empty space between
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth(fraction = 0.9f)
                        .background(brush)
                )
            }
        }
    }
}