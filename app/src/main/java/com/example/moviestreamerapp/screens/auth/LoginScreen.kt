package com.example.moviestreamerapp.screens.auth

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.moviestreamerapp.R
import com.example.moviestreamerapp.customLayouts.AuthHorizontalDivider
import com.example.moviestreamerapp.customLayouts.AuthTextButton
import com.example.moviestreamerapp.customLayouts.CustomTextFields
import com.example.moviestreamerapp.customLayouts.PrimaryButton
import com.example.moviestreamerapp.customLayouts.SocialAppsNavButtons
import com.example.moviestreamerapp.customLayouts.TopBar
import com.example.moviestreamerapp.ui.theme.PrimaryRed
import com.example.moviestreamerapp.ui.theme.notoBold
import com.example.moviestreamerapp.ui.theme.notoSemiBold

@Composable
fun LoginScreen(modifier: Modifier, onBackClick:()->Unit,onSignUpClick:()->Unit,onForgotClick:()->Unit){
    var rememberStatus by remember { mutableStateOf(false) }
    Column(modifier.fillMaxSize()) {
        TopBar(Modifier, null, onBackClick)
        Column(Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(R.drawable.movie_logo2),
                contentDescription = null,
                Modifier.size(width=90.dp, height = 130.dp),
                tint = Color.Unspecified
            )
            Text(
                text = stringResource(R.string.auth_login),
                color = Color.White,
                fontSize = 30.sp,
                letterSpacing = 0.01.em,
                lineHeight = TextUnit.Unspecified,
                fontFamily = notoBold
            )
        }

       Column(Modifier.fillMaxWidth(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.spacedBy(16.dp,Alignment.Top)) {
        CustomTextFields("it",
            onValueChange = {},
            R.drawable.email_icon,null,
            keyboardType = KeyboardType.Email,
            placeholder = "Email",

        )
        CustomTextFields("it",
            onValueChange = {},
            R.drawable.password_icon,null,
            keyboardType = KeyboardType.Password,
            placeholder = "Password"
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = rememberStatus, onCheckedChange = {rememberStatus=!rememberStatus}, enabled = true,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Blue,
                ))
            Text(
                text = "Remember me",
                Modifier.padding(start = 12.dp),
                color = Color.White,
                fontFamily = notoSemiBold,
                fontSize = 14.sp
            )
        }
        PrimaryButton(PrimaryRed,"Sign in", onClick = {}, Modifier.fillMaxWidth())
        TextButton(onClick = { onForgotClick() }) {
            Text(
                text = "Forgot Password?",
                fontFamily = notoSemiBold,
                color = PrimaryRed
            )
        }
    }
        Column(Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            AuthHorizontalDivider("or continue with")

            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp,Alignment.CenterHorizontally)
            ) {
                SocialAppsNavButtons(Modifier,{},R.drawable.facebook_icon,null)
                SocialAppsNavButtons(Modifier,{},R.drawable.google_icon,null)
                SocialAppsNavButtons(Modifier,{},R.drawable.apple_icon,null)
            }
        }

        AuthTextButton(Modifier.padding(top = 16.dp),onSignUpClick,stringResource(R.string.signup_title),"Sign up")
    }

}