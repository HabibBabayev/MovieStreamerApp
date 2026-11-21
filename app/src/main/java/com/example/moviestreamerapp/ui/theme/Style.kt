package com.example.moviestreamerapp.ui.theme

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.text.input.TextFieldTextStyles
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ButtonColorStyle(): IconButtonColors{
    val color=IconButtonColors(
        containerColor = PrimaryDarkGrayColor,
        contentColor = Color.Black,
        disabledContentColor = Color.Unspecified,
        disabledContainerColor = PrimaryDarkGrayColor
    )
    return color
}
@Composable
fun OutlinedTextFieldColors(): TextFieldColors{
    val color= TextFieldDefaults.colors(
        unfocusedLeadingIconColor = LighterGray,
        unfocusedPlaceholderColor = LighterGray,
        disabledLeadingIconColor = LighterGray,
        cursorColor = LighterGray,
        disabledIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Unspecified,
        unfocusedContainerColor = PrimaryLightGrayColor,
        focusedContainerColor = PrimaryLightGrayColor,
        unfocusedIndicatorColor = PrimaryLightGrayColor,



    )
    return color
}
@Composable
fun forgotPassFieldColor()=OutlinedTextFieldDefaults.colors(
    focusedContainerColor = PrimaryLightGrayColor,
    unfocusedContainerColor = PrimaryLightGrayColor,
    focusedBorderColor = LighterGray,
    unfocusedBorderColor = LighterGray,
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,
    cursorColor = LighterGray

)
val authTextFieldTextStyle=TextStyle(color = LighterGray, fontFamily = notoRegular, fontWeight = FontWeight.W400)
fun <T>AnimateChanges(timeMillis: Int): AnimationSpec<T> {
    val animateTo: AnimationSpec<T> = tween(
        durationMillis = timeMillis
    )
    return animateTo
}

