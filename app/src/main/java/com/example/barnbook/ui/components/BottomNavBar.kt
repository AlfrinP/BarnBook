package com.example.barnbook.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class NavItems(
    val label: String,
    val icon: Int = 0,
    val onClick: () -> Unit = {}
)

@Preview
@Composable
fun BottomNavBar() {
    val navItems = listOf(
        NavItems("Home"),
        NavItems("Activity"),
        NavItems("Add"),
        NavItems("Items"),
        NavItems("Profile")
    )
    NavigationBar(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
    ) {
        navItems.forEach { navItem ->
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (navItem.label != "Add") {
                    Text(text = navItem.label)
                } else {
                    Button(
                        onClick = navItem.onClick,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(10.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = navItem.label)
                    }
                }
            }
        }
    }
}