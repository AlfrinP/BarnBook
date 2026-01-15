package com.example.barnbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.barnbook.ui.components.BottomNavBar
import com.example.barnbook.ui.theme.BarnBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BarnBookTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f))
        BottomNavBar()
    }
}

data class UserData(
    val img: Int = 0,
    val name: String,
    val email: String,
    val phone: String,
    val darkMode: Boolean,
    val remainder: String,
    val createdAt: String
)

@Composable
fun ProfileScreen() {
    val demoUserData = UserData(
        img = 0,
        name = "John Doe",
        email = "example@123.com",
        phone = "123456789",
        darkMode = false,
        remainder = "12:07 AM",
        createdAt = "12:07 AM"
    )

    fun profileScreenUiText(
        label: String,
        text: String,
        labelStyle: TextStyle = TextStyle.Default,
        textStyle: TextStyle = TextStyle.Default
    ): AnnotatedString {
        return buildAnnotatedString {
            withStyle(style = labelStyle.toSpanStyle()) {
                append(label)
            }
            append(" : ")
            withStyle(style = textStyle.toSpanStyle()) {
                append(text)
            }
        }
    }

    Box() {
        Box() {
//            Image(
//                painter = demoUserData.img,
//                contentDescription = "user profile image",
//            )
        }
        Column() {
            Text(text = profileScreenUiText(text = demoUserData.name, label = "Name"))
            Text(text = profileScreenUiText(text = demoUserData.email, label = "Email"))
            Text(text = profileScreenUiText(text = demoUserData.phone, label = "Phone"))
            Text(text = profileScreenUiText(text = demoUserData.phone, label = "Dark Mode"))
            Text(
                text = profileScreenUiText(
                    text = demoUserData.remainder,
                    label = "Daily Remainder"
                )
            )
            Text(text = profileScreenUiText(text = demoUserData.createdAt, label = "Started On"))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BarnBookTheme {
        HomeScreen()
    }
}