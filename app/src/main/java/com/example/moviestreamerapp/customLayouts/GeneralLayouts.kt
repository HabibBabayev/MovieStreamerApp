package com.example.moviestreamerapp.customLayouts
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.moviestreamerapp.R
import com.example.moviestreamerapp.general.CountryPhoneCode
import com.example.moviestreamerapp.ui.theme.AnimateChanges
import com.example.moviestreamerapp.ui.theme.LighterGray
import com.example.moviestreamerapp.ui.theme.OutlinedTextFieldColors
import com.example.moviestreamerapp.ui.theme.PrimaryLightGrayColor
import com.example.moviestreamerapp.ui.theme.PrimaryRed
import com.example.moviestreamerapp.ui.theme.authTextFieldTextStyle
import com.example.moviestreamerapp.ui.theme.notoRegular
import com.example.moviestreamerapp.ui.theme.notoSemiBold
import kotlinx.coroutines.delay

@Composable
fun SloganSlider(pagerState: PagerState, content:List<String>, modifier: Modifier){

    LaunchedEffect(pagerState,content.size){
        if (content.isNotEmpty()){
            while (true){
                delay(3000)
                val nextPage= (pagerState.currentPage+1)%content.size
                pagerState.animateScrollToPage(nextPage,
                    animationSpec = AnimateChanges(500)
                )
            }
        }
    }
        HorizontalPager(pagerState,
            modifier =Modifier.fillMaxWidth()
        ) { page->
            Text(text = content[page],
                modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp),
                style = TextStyle(
                    fontFamily = notoRegular,
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.em
                ))
        }
    SliderBar(content,pagerState)
    }

@Composable
fun SliderBar(content: List<String>,pager: PagerState){

    Row(Modifier.wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(5.dp)) {

        repeat(content.size){
            val isActivePage=pager.currentPage==it
            val colorChange by animateColorAsState(
                targetValue = if (isActivePage) PrimaryRed else Color.White,
                animationSpec = AnimateChanges(500)
            )
            val sizeChange by animateDpAsState(
                targetValue = if (isActivePage) 36.dp else 8.dp,
                animationSpec = AnimateChanges(500))
                Box(Modifier.size(width= sizeChange, height = 8.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(colorChange)
                    .animateContentSize()
                ) {

                }

        }
    }
}

@Composable
fun PrimaryButton(color:Color,text: String, onClick:()->Unit, modifier: Modifier){
    FilledTonalButton(onClick = {onClick()},
        modifier = modifier
            .height(56.dp),
        shape= ShapeDefaults.ExtraLarge,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = color,
            contentColor = Color.White,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.Transparent
        )
            ) {
        Text(text = text,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = notoSemiBold,


            )
        )
    }
}

@Composable
fun CustomTextFields(text: String,
                     onValueChange:(text: String)->Unit,
                     leadingIcon: Int?,
                     trailingIcon:Int?,
                     placeholder: String,
                     keyboardType: KeyboardType){

    var passwordVisibility by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = text,
            onValueChange = {
                onValueChange(it)
            },
            Modifier.fillMaxWidth()
                .height(58.dp),
            colors = OutlinedTextFieldColors(),
            textStyle = authTextFieldTextStyle,
            leadingIcon = when {
                leadingIcon != null -> {{
                        Icon(
                            imageVector = ImageVector.vectorResource(leadingIcon),
                            contentDescription = null,
                            tint = LighterGray
                        )
                    }
                }
                else -> null
            },
            placeholder = {Text(placeholder,
                fontSize = 16.sp,
                fontFamily = notoRegular,
                color = LighterGray
                )},
            trailingIcon = when {
                keyboardType== KeyboardType.Password && leadingIcon==R.drawable.password_icon ->{{
                    Icon(imageVector = ImageVector.vectorResource(
                        if (passwordVisibility) R.drawable.eye_on else R.drawable.eye_off)
                        ,contentDescription = null,
                        Modifier.size(20.dp)
                            .clickable{
                                passwordVisibility=!passwordVisibility
                            },
                        tint = LighterGray
                    )
                } }
                trailingIcon !=null->{{
                    Icon(imageVector = ImageVector.vectorResource(trailingIcon)
                        ,contentDescription = null,
                        Modifier.size(20.dp),
                        tint = LighterGray
                    )
                }}


                else -> null
                                },
            keyboardOptions = KeyboardOptions(keyboardType= keyboardType),
            visualTransformation = if (passwordVisibility || keyboardType!= KeyboardType.Password) VisualTransformation.None else PasswordVisualTransformation(),
            shape = ShapeDefaults.Large,
            )
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneDropDownMenuField(){
    val countryList=listOf<CountryPhoneCode>(CountryPhoneCode(R.drawable.us_icon,"1","USA") ,
        CountryPhoneCode(R.drawable.rus_icon,"7","USA"),
        CountryPhoneCode(R.drawable.tr_icon,"90","USA"),
        CountryPhoneCode(R.drawable.az_icon,"994","USA"))
    var expand by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    var countryFlag by remember{mutableIntStateOf(R.drawable.us_icon)}
    Box(Modifier.fillMaxWidth()) {
        OutlinedTextField(value = text,
            onValueChange = {
                text=it
            },
            Modifier.fillMaxWidth().height(58.dp).alpha(1f),
            placeholder = {Text("Phone Number",
                Modifier.padding(start = 10.dp),
                fontSize = 16.sp,
                fontFamily = notoRegular,
                color = LighterGray,

            )},
            colors= OutlinedTextFieldColors(),
            shape = ShapeDefaults.Large,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon ={
                Row(Modifier.clickable(enabled = true,
                    onClick = { expand=!expand })
                    .padding(start = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(painter = painterResource(countryFlag),
                        contentDescription = null,
                        Modifier.size(20.dp),
                        tint = Color.Unspecified)
                    Icon(painter = painterResource(R.drawable.down_vector),
                        contentDescription = null,
                        Modifier,
                        tint = LighterGray)

                }
            },
            textStyle = authTextFieldTextStyle,

            )
        DropdownMenu(expanded = expand,
            onDismissRequest = {expand=!expand},
            Modifier.width(50.dp),
            shape = ShapeDefaults.Large,
            containerColor = PrimaryLightGrayColor){
            countryList.forEach{icon->
                DropdownMenuItem(
                    text={
                        Icon(painter =painterResource(icon.image),
                            contentDescription = null,
                            Modifier.size(32.dp),
                            tint = Color.Unspecified)
                    },
                    onClick = {
                        text=icon.code
                        countryFlag=icon.image
                        expand=!expand
                    }
                )
            }

        }
    }

}






