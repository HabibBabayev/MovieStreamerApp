package com.example.moviestreamerapp.screens.auth

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import com.example.moviestreamerapp.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.moviestreamerapp.customLayouts.CustomForgotPasswordTextField
import com.example.moviestreamerapp.customLayouts.PrimaryButton
import com.example.moviestreamerapp.customLayouts.TopBar
import com.example.moviestreamerapp.ui.theme.LighterGray
import com.example.moviestreamerapp.ui.theme.OutlinedTextFieldColors
import com.example.moviestreamerapp.ui.theme.PrimaryLightGrayColor
import com.example.moviestreamerapp.ui.theme.PrimaryRed
import com.example.moviestreamerapp.ui.theme.forgotPassFieldColor
import com.example.moviestreamerapp.ui.theme.notoLight
import com.google.firebase.auth.PhoneAuthOptions
import kotlinx.coroutines.delay

@Composable
fun ForgotPasswordScreen(modifier:Modifier,onBackClick:()-> Unit,totalDigits: Int){
    var numberList by remember {
        mutableStateOf(List(totalDigits){""})
    }
    val option= PhoneAuthOptions.newBuilder()
    var focuses = remember { List(totalDigits){ FocusRequester() } }
    //for each number we create focus requester
    var timer by remember { mutableIntStateOf(10) }
    var isCountDown by remember { mutableStateOf(true) }
    var resendCode by remember { mutableStateOf(false) }
    LaunchedEffect(isCountDown) {
        if (isCountDown){
            while (timer>0) {
                delay(1000)
                timer--

            }
            //during recomposition return to first textfield requested
            focuses.first().requestFocus()
        }

        if (timer==0) isCountDown=false
    }
    Box(modifier.fillMaxSize()){
        Column(Modifier.fillMaxWidth()
            .align(Alignment.TopCenter)) {
            TopBar(Modifier, stringResource(R.string.forgot_password), onBackClick = {onBackClick()})
            Spacer(Modifier.padding(100.dp))
            Column(Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(28.dp),
                ) {
                Text("Code has been sent to +994*******26",
                    color=Color.White,
                    fontFamily = notoLight
                )
                Row(Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        repeat(totalDigits){index->
                            CustomForgotPasswordTextField(totalDigits,index,numberList,focuses,{newList->
                                numberList=newList
                            })
                    }
                }
                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = notoLight,
                    )){
                        append("Resend code in ")
                    }
                    withStyle(SpanStyle(color= PrimaryRed)){
                        append(timer.toString())
                    }
                    withStyle(SpanStyle(
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = notoLight
                    )){
                        append(" s")
                    }
                })
            }
        }
        if (!isCountDown){
            PrimaryButton(PrimaryRed,"Resend Code",{
                isCountDown=true
                timer=60
                numberList=List(totalDigits){""}
            }, Modifier.align(Alignment.BottomCenter).fillMaxWidth())
        } else PrimaryButton(PrimaryRed,"Verify",{},Modifier.align(Alignment.BottomCenter).fillMaxWidth())

    }
}