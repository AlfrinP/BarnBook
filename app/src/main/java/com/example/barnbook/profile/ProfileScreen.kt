package com.example.barnbook.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class UserData(
    val img: Int = 0,
    val name: String,
    val email: String,
    val phone: String,
    val darkMode: Boolean,
    val remainder: String,
    val createdAt: String
)

@Preview
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
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {

            ProfileUiListItem(
                label = "Name",
                text = demoUserData.name
            )

            ProfileUiListItem(
                label = "Email",
                text = demoUserData.email
            )

            ProfileUiListItem(
                label = "Phone",
                text = demoUserData.phone
            )

            ProfileUiListItem(
                label = "Daily Reminder",
                text = demoUserData.remainder
            )

            ProfileUiListItem(
                label = "Dark Mode"
            ) {
                DarkModeSelection()
            }

            ProfileUiListItem(
                label = "Started On",
                text = demoUserData.createdAt
            )
        }
    }
}

@Composable
fun ProfileUiListItem(
    label: String,
    text: String? = null,
    content: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label)
        Text(text = " : ")

        when {
            content != null -> content()
            text != null -> Text(text = text)
        }
    }
}

@Composable
fun DarkModeSelection() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Dark", "Light")

    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = { selectedIndex = index },
                selected = index == selectedIndex,
                label = { Text(label) }
            )
        }
    }
}