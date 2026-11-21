package com.example.moviestreamerapp.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviestreamerapp.R
import com.example.moviestreamerapp.customLayouts.CustomTextFields
import com.example.moviestreamerapp.customLayouts.PhoneDropDownMenuField
import com.example.moviestreamerapp.customLayouts.PrimaryButton
import com.example.moviestreamerapp.customLayouts.ProfilePictureSetup
import com.example.moviestreamerapp.customLayouts.TopBar
import com.example.moviestreamerapp.ui.theme.LighterGray
import com.example.moviestreamerapp.ui.theme.PrimaryGray
import com.example.moviestreamerapp.ui.theme.PrimaryLightGrayColor
import com.example.moviestreamerapp.ui.theme.PrimaryRed
import com.example.moviestreamerapp.ui.theme.authTextFieldTextStyle
import com.example.moviestreamerapp.ui.theme.notoBold
import com.example.moviestreamerapp.ui.theme.notoSemiBold

@Composable
fun NewPasswordScreen(modifier: Modifier,onBackClick:()-> Unit){
    var rememberMeStatus by remember { mutableStateOf(false) }
    val interactSource = remember{ MutableInteractionSource() }
    Column(modifier.fillMaxSize()){
        Column(Modifier.weight(1f)) {
            TopBar(Modifier.padding(bottom = 24.dp), stringResource(R.string.new_password),{onBackClick()})
            Column(Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Box(Modifier.padding(vertical = 16.dp)) {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.main_illustration),
                        contentDescription = null,
                        Modifier.align(Alignment.Center),
                        tint = Color.Unspecified
                        )
                }
                Column(Modifier.weight(1f)
                    .padding(top = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top)) {
                    Text(text = "Create Your New Password",
                        Modifier.fillMaxWidth(),
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = notoBold,
                            fontSize = 18.sp
                        )
                    )
                    CustomTextFields("it",
                        onValueChange = {},R.drawable.password_icon,R.drawable.eye_off,"Password", KeyboardType.Password)
                    CustomTextFields("it",
                        onValueChange = {},R.drawable.password_icon,R.drawable.eye_off,"Retype Password", KeyboardType.Password)
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = rememberMeStatus,onCheckedChange = {rememberMeStatus=!rememberMeStatus}, interactionSource = interactSource,colors = CheckboxDefaults.colors(checkedColor = PrimaryRed, uncheckedColor = LighterGray))

                        Text(
                            text = "Remember me",
                            Modifier.padding(start = 12.dp),
                            color = Color.White,
                            fontFamily = notoSemiBold,
                            fontSize = 14.sp
                        )

                    }


                }
            }
        }


        Row(
            androidx.compose.ui.Modifier.fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {

            PrimaryButton(PrimaryRed,"Continue",{}, Modifier.fillMaxWidth())

        }
    }

}
