package com.example.barnbook.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.barnbook.R

data class NavItems(
    val label: String,
    val icon: Int = 0,
    val onClick: () -> Unit = {}
)

@Preview
@Composable
fun BottomNavBar() {
    val navItems = listOf(
        NavItems("Home", R.drawable.home),
        NavItems("Activity", R.drawable.activity),
        NavItems("Add", R.drawable.plus),
        NavItems("Items", R.drawable.items),
        NavItems("Profile", R.drawable.profile)
    )
    NavigationBar(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
    ) {
        navItems.forEach { navItem ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = if (navItem.label == "Add") Color.LightGray else Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(navItem.icon),
                    contentDescription = navItem.label
                )
                Text(text = navItem.label, fontSize = 12.sp)
            }
        }
    }
}