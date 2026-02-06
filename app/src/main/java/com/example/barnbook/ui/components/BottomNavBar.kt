package com.example.barnbook.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.barnbook.R
import com.example.barnbook.ui.theme.FreshGreen

data class NavItems(
    val label: String,
    val icon: Int = 0,
    val route: String = "",
    val onClick: () -> Unit = {}
)

@Composable
fun BottomNavBar(
    onHomeClick: () -> Unit,
    onActivityClick: () -> Unit,
    onItemsClick: () -> Unit,
    onProfileClick: () -> Unit,
) {
    var selectedItem by remember { mutableStateOf("Home") }

    val navItems = listOf(
        NavItems("Home", R.drawable.home, "home", onClick = onHomeClick),
        NavItems("Activity", R.drawable.activity, "activity", onClick = onActivityClick),
        NavItems("Add", R.drawable.plus, "add", onClick = onHomeClick),
        NavItems("Items", R.drawable.items, "items", onClick = onItemsClick),
        NavItems("Profile", R.drawable.profile, "profile", onClick = onProfileClick)
    )

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        navItems.forEach { navItem ->
            val isSelected = selectedItem == navItem.label
            val isAddButton = navItem.label == "Add"

            if (isAddButton) {
                // Special FAB-style Add button
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .offset(y = (-8).dp)
                            .clip(CircleShape)
                            .background(
                                brush = verticalGradient(
                                    colors = listOf(
                                        FreshGreen,
                                        FreshGreen.copy(alpha = 0.9f)
                                    )
                                )
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                onClick = {
                                    selectedItem = navItem.label
                                    navItem.onClick()
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(navItem.icon),
                            contentDescription = navItem.label,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            } else {
                // Regular navigation items
                NavBarItem(
                    navItem = navItem,
                    isSelected = isSelected,
                    onClick = {
                        selectedItem = navItem.label
                        navItem.onClick()
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun NavBarItem(
    navItem: NavItems,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconColor by animateColorAsState(
        targetValue = if (isSelected) FreshGreen else MaterialTheme.colorScheme.onSurfaceVariant,
        animationSpec = tween(durationMillis = 300),
        label = "iconColor"
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected) FreshGreen else MaterialTheme.colorScheme.onSurfaceVariant,
        animationSpec = tween(durationMillis = 300),
        label = "textColor"
    )

    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.1f else 1f,
        animationSpec = tween(durationMillis = 300),
        label = "scale"
    )

    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
    ) {
        Icon(
            painter = painterResource(navItem.icon),
            contentDescription = navItem.label,
            tint = iconColor,
            modifier = Modifier
                .size(24.dp)
                .scale(scale)
        )

        Text(
            text = navItem.label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = textColor
        )
    }
}
