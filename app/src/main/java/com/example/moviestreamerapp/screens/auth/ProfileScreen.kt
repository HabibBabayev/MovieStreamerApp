package com.example.moviestreamerapp.screens.auth

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
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

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moviestreamerapp.R
import com.example.moviestreamerapp.customLayouts.CustomTextFields
import com.example.moviestreamerapp.customLayouts.PhoneDropDownMenuField
import com.example.moviestreamerapp.customLayouts.PrimaryButton
import com.example.moviestreamerapp.customLayouts.ProfilePictureSetup
import com.example.moviestreamerapp.customLayouts.TopBar
import com.example.moviestreamerapp.ui.theme.AnimateChanges
import com.example.moviestreamerapp.ui.theme.LighterGray
import com.example.moviestreamerapp.ui.theme.PrimaryGray
import com.example.moviestreamerapp.ui.theme.PrimaryLightGrayColor
import com.example.moviestreamerapp.ui.theme.PrimaryRed
import com.example.moviestreamerapp.ui.theme.authTextFieldTextStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier,onBackClick:()->Unit){
    val genders=listOf("Man","Woman")
    val interactionSource = remember { MutableInteractionSource() }
    var expandMenu by remember { mutableStateOf(false) }
    val expandDp by animateDpAsState(
        targetValue = if (expandMenu) 116.dp else 58.dp,
        animationSpec = AnimateChanges(300)
    )
    Column(modifier.fillMaxSize()){
        Column(Modifier.weight(1f)) {
            TopBar(Modifier.padding(bottom = 24.dp), stringResource(R.string.profile),{onBackClick()})
            Column(Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Box(Modifier.padding(vertical = 12.dp)) {
                    ProfilePictureSetup(Modifier.align(Alignment.Center).alpha(0.8f),null)
                    Box(
                        Modifier.size(height = 38.dp, width = 44.dp)
                            .padding(end = 6.dp)
                            .clickable(enabled = true,onClick = {})
                            .alpha(1f)
                            .align(Alignment.BottomEnd)
                            .background(PrimaryRed, RoundedCornerShape(12.dp))
                    ) {

                        Icon(painterResource(R.drawable.vector),
                            contentDescription = null,
                            Modifier.align(Alignment.Center)
                                .size(12.dp),
                            tint = Color.Unspecified)
                    }
                }
                Column(Modifier.weight(1f)
                    .padding(top = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top)) {
                    CustomTextFields("it",
                        onValueChange = {},null,null,"Full Name", KeyboardType.PersonName)
                    CustomTextFields("it",
                        onValueChange = {},null,null,"NickName", KeyboardType.Unspecified)
                    CustomTextFields("it",
                        onValueChange = {},null,R.drawable.email_icon,"Email", KeyboardType.Unspecified)
                        PhoneDropDownMenuField()


                    Column(Modifier.fillMaxWidth()
                        .height(expandDp)
                        .clickable(enabled = true, onClick = {expandMenu=true
                        }, interactionSource = interactionSource,
                            indication = null)
                        .background(PrimaryLightGrayColor, shape = ShapeDefaults.Large),
                        horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                        if (!expandMenu){
                            Row(Modifier.fillMaxSize().height(expandDp)
                                .padding(start = 16.dp, end = 18.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "Genders",
                                    style = authTextFieldTextStyle,

                                    )
                                Icon(painterResource(R.drawable.down_vector),
                                    contentDescription = null,
                                    tint = LighterGray)
                            }
                            } else {
                            Row(Modifier.fillMaxSize().height(expandDp)
                                .padding(start = 16.dp, end = 18.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically) {
                                Column(Modifier.fillMaxSize()
                                    .padding(vertical = 14.dp),
                                    verticalArrangement = Arrangement.SpaceBetween
                                    ) {
                                    genders.forEach {

                                            Text(text=it,
                                                Modifier.fillMaxWidth()
                                                    .clickable(enabled = true, onClick = {
                                                        expandMenu=!expandMenu
                                                    })
                                                    .height(58.dp),
                                                style = authTextFieldTextStyle,
                                            )
                                    }
                                }


                            }
                        }
                        }

                    }
                }
            }
        Row(
            androidx.compose.ui.Modifier.fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            PrimaryButton(PrimaryGray,"Skip",{}, Modifier.width(172.dp))
            PrimaryButton(PrimaryRed,"Continue",{}, Modifier.width(172.dp))

        }
        }

    }
