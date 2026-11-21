package com.example.moviestreamerapp.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviestreamerapp.R
import com.example.moviestreamerapp.customLayouts.AuthHorizontalDivider
import com.example.moviestreamerapp.customLayouts.AuthTextButton
import com.example.moviestreamerapp.customLayouts.PrimaryButton
import com.example.moviestreamerapp.customLayouts.SocialAppsNavButtons
import com.example.moviestreamerapp.customLayouts.TopBar
import com.example.moviestreamerapp.ui.theme.PrimaryRed
import com.example.moviestreamerapp.ui.theme.notoBold

@Composable
fun AuthEntryScreen(modifier: Modifier, onBackClick:()-> Unit,onSignInClick:()-> Unit,onSignUpClick:()-> Unit){
    Column(modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth()
            .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopBar(modifier=Modifier,null,onBackClick)
            Image(painter = painterResource(R.drawable.auth_logo),
                contentDescription = null,
                Modifier.size(197.dp)
                    .padding(16.dp))
            Text(text = stringResource(R.string.auth_start),
                Modifier.padding(vertical = 16.dp),
                color = Color.White,
                fontSize = 36.sp,
                fontFamily = notoBold,
            )
            SocialAppsNavButtons(Modifier,{},R.drawable.facebook_icon,"Continue with Facebook")
            SocialAppsNavButtons(Modifier,{},R.drawable.google_icon,"Continue with Google")
            SocialAppsNavButtons(Modifier.padding(bottom = 16.dp),{},R.drawable.apple_icon,"Continue with Apple")
            AuthHorizontalDivider("or")
            PrimaryButton(PrimaryRed,stringResource(R.string.auth_intro_button_title),onSignInClick,Modifier.padding(vertical = 16.dp).fillMaxWidth())
            Spacer(Modifier.padding(10.dp))

        }
        AuthTextButton(Modifier,onSignUpClick,stringResource(R.string.signup_title),"Sign up")

    }

}