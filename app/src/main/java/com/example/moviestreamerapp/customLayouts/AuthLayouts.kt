package com.example.moviestreamerapp.customLayouts

import android.R.attr.contentDescription
import android.R.attr.onClick
import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.autofill.contentType
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.moviestreamerapp.R
import com.example.moviestreamerapp.ui.theme.ButtonColorStyle
import com.example.moviestreamerapp.ui.theme.LighterGray
import com.example.moviestreamerapp.ui.theme.PrimaryDarkGrayColor
import com.example.moviestreamerapp.ui.theme.PrimaryGray
import com.example.moviestreamerapp.ui.theme.PrimaryLightGrayColor

import com.example.moviestreamerapp.ui.theme.PrimaryRed
import com.example.moviestreamerapp.ui.theme.forgotPassFieldColor
import com.example.moviestreamerapp.ui.theme.notoBold
import com.example.moviestreamerapp.ui.theme.notoLight
import com.example.moviestreamerapp.ui.theme.notoMedium
import com.example.moviestreamerapp.ui.theme.notoSemiBold

@Composable
fun SocialAppsNavButtons(modifier:Modifier,onClick: () -> Unit,icon:Int,title: String?){
    if (title.isNullOrBlank()){

        FilledTonalIconButton(onClick=onClick,
            modifier = Modifier
                .size(width = 80.dp,height = 56.dp)
                .border(BorderStroke(1.dp, PrimaryLightGrayColor), shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = IconButtonColors(PrimaryDarkGrayColor,
                PrimaryRed,
                Color.Black,
                Color.Black),
        ) {
            Icon(painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified)
        }
    } else{
        FilledTonalButton(onClick=onClick,
            modifier = modifier.fillMaxWidth()
                .padding(top = 16.dp)
                .height(56.dp)
                .border(BorderStroke(1.dp, PrimaryLightGrayColor), shape = RoundedCornerShape(16.dp) ),
            shape = RoundedCornerShape(16.dp),
            colors= ButtonDefaults.filledTonalButtonColors(
                PrimaryDarkGrayColor
            )) {
            Icon(painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Unspecified)
            Spacer(Modifier.padding(6.dp))
            Text(text = title,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 15.sp,
                    fontFamily = notoSemiBold
                )
            )

        }
    }
}


@Composable
fun TopBar(modifier: Modifier,header: String?,onBackClick:()->Unit){
    Row(modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (header.isNullOrBlank()){

                Icon(painter = painterResource(R.drawable.back_stroke),
                    contentDescription = null,
                    Modifier.clickable( onClick = onBackClick)
                        .padding(vertical = 4.dp),
                    tint = Color.White)

        } else {

                Icon(painter = painterResource(R.drawable.back_stroke),
                    contentDescription = null,
                    Modifier.clickable(onClick = onBackClick)
                        .padding(vertical = 4.dp),
                    tint = Color.White)

            Text(text = header,
                textAlign = TextAlign.Center,
                modifier=Modifier.padding(start = 16.dp),
                style = TextStyle(
                    color = Color.White,
                    fontFamily = notoBold,
                    fontSize = 24.sp,
                    lineHeight = 32.sp)
            )
        }
    }
}
@Composable
fun AuthTextButton(modifier:Modifier,onClick: () -> Unit,text: String,buttonText: String){
    Row(modifier.fillMaxWidth().padding(top = 24.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        Text(text,
            style = TextStyle(
                color = LighterGray,
                fontFamily = notoLight
            ))
        TextButton(onClick) {
            Text(buttonText,
                style = TextStyle(
                    color = PrimaryRed,
                    fontFamily = notoBold
                )
            )
        }
    }

}
@Composable
fun AuthHorizontalDivider(text: String){
    Row(Modifier.fillMaxWidth()
        .padding(vertical = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(Modifier.weight(1f),color = PrimaryLightGrayColor)
        Text(text, Modifier.padding(horizontal = 8.dp), color = LighterGray, fontFamily = notoMedium)
        HorizontalDivider(Modifier.weight(1f),color = PrimaryLightGrayColor)

    }

}
@Composable
fun CustomForgotPasswordTextField(totalDigits: Int,index: Int,numberList: List<String>,focuses:List<FocusRequester>,sendCode:(number:List<String>)->Unit){

    //for each number we create focus requester

    OutlinedTextField(
        value = numberList[index],
        onValueChange = {digit->
            //if only one value added and it is only digit then it can fill blank space
            if (digit.length<=1 && digit.all{it.isDigit()}){
              val  newList= numberList.toMutableList().also{numberList->
                    numberList[index]=digit}
                sendCode(newList)
                //changing focuses
                if (digit.isNotEmpty()){
                    //to limit amount of total digits added to 4
                    if (index<totalDigits-1){
                        //to change focus to the next textField
                        focuses[index+1].requestFocus()
                    }
                }else {
                    //if textfild is empty it goes to previous textfield box
                    if (index>0){
                        focuses[index-1].requestFocus()
                    }
                }
            }

        },
        Modifier.focusRequester(focuses[index])
            .contentType(ContentType.SmsOtpCode)
            .size(height = 62.dp, width = 72.dp)
            .border(1.dp,LighterGray, ShapeDefaults.Medium),
        shape = ShapeDefaults.Medium,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        colors = forgotPassFieldColor(),
        textStyle = TextStyle(
            color=Color.White,
            fontFamily = notoBold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    )
}
@Composable
fun ProfilePictureSetup(modifier: Modifier,image: Image?){
    Box(Modifier.size(150.dp)

        .background(PrimaryGray, shape = CircleShape)
        .clip(CircleShape)) {
            Icon(painterResource(R.drawable.profile_logo),
                contentDescription = null,
                Modifier.align(Alignment.BottomCenter)
                    .size(144.dp)
                    .offset(x=0.dp,y=(10).dp),
                tint = Color(0xFF4B4E57))

    }
}