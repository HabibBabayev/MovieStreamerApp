package com.example.moviestreamerapp.screens.root

import android.R.attr.fontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

import com.example.moviestreamerapp.R
import com.example.moviestreamerapp.customLayouts.PrimaryButton
import com.example.moviestreamerapp.customLayouts.SliderBar
import com.example.moviestreamerapp.customLayouts.SloganSlider
import com.example.moviestreamerapp.ui.theme.OverlayColor
import com.example.moviestreamerapp.ui.theme.OverlayColor2
import com.example.moviestreamerapp.ui.theme.OverlayColor3
import com.example.moviestreamerapp.ui.theme.PrimaryRed
import com.example.moviestreamerapp.ui.theme.notoExtraBold
import com.example.moviestreamerapp.ui.theme.notoRegular

@Composable
fun IntroScreen(modifier: Modifier,onAuthClick:()->Unit){
    val sloganList=listOf<String>(
        stringResource(R.string.intro_slogan1),
        stringResource(R.string.intro_slogan2),
        stringResource(R.string.intro_slogan3))
    val pagerState = rememberPagerState(
        pageCount = {sloganList.size},
        initialPage = 0
    )
Box(Modifier.fillMaxSize()
    ) {
    Image(painter = painterResource(R.drawable.intro_pic),
        contentDescription = "intro_picture",
        Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)
    Box(Modifier.fillMaxSize().background(brush=Brush.verticalGradient(
        listOf(
            OverlayColor.copy(0.45f),
            OverlayColor.copy(0.25f),
            OverlayColor.copy(0.85f),
            OverlayColor2,
        )
    ) )) { }

Column(Modifier.fillMaxWidth()
    .align(Alignment.BottomCenter), horizontalAlignment = Alignment.CenterHorizontally) {

    Text(text = stringResource(R.string.intro_header),
        style = TextStyle(
            fontFamily = notoExtraBold,
            fontSize = 30.sp,
            color = Color.White
        ))
    SloganSlider(pagerState,modifier=modifier, content = sloganList)
    PrimaryButton(PrimaryRed,text =stringResource(R.string.intro_button_title) ,onClick = {onAuthClick()}, modifier = modifier.padding(vertical = 16.dp).fillMaxWidth())
}
}
}