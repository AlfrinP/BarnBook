package com.example.barnbook.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.barnbook.R
import com.example.barnbook.ui.components.StatCard
import com.example.barnbook.ui.theme.AnimalBrown
import com.example.barnbook.ui.theme.GoalBlue
import com.example.barnbook.ui.theme.PlantGreen
import com.example.barnbook.ui.theme.ProduceGreen

@Preview(showBackground = true)
@Composable
fun Home() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        
        // Header Section with Greeting
        item {
            HeaderSection(
                userName = "Alfrin",
                itemsCollectedToday = 8
            )
        }
        
        // Statistics Cards Grid (2x2)
        item {
            StatisticsGrid()
        }
        
        // Analytics Graph Section
        item {
            AnalyticsSection()
        }
        
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun HeaderSection(
    userName: String,
    itemsCollectedToday: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        ProduceGreen.copy(alpha = 0.1f),
                        PlantGreen.copy(alpha = 0.15f)
                    )
                )
            )
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Good morning, $userName",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Today you collected",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(ProduceGreen)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "$itemsCollectedToday items",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun StatisticsGrid() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Today's Summary",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        // First Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StatCard(
                label = "Produce Tracked",
                value = "12",
                icon = R.drawable.items,
                backgroundColor = ProduceGreen,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                label = "Goals Tracked",
                value = "5",
                icon = R.drawable.activity,
                backgroundColor = GoalBlue,
                modifier = Modifier.weight(1f)
            )
        }
        
        // Second Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StatCard(
                label = "Animals",
                value = "8",
                icon = R.drawable.activity,
                backgroundColor = AnimalBrown,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                label = "Plants",
                value = "4",
                icon = R.drawable.items,
                backgroundColor = PlantGreen,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun AnalyticsSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Weekly Overview",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Text(
                text = "Plants vs Animal Produces",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            // Bar Chart
            SimpleBarChart(
                plantsCount = 45,
                animalsCount = 32
            )
            
            // Legend
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                LegendItem(
                    label = "Plants",
                    color = PlantGreen,
                    value = "45"
                )
                LegendItem(
                    label = "Animals",
                    color = AnimalBrown,
                    value = "32"
                )
            }
        }
    }
}

@Composable
fun SimpleBarChart(
    plantsCount: Int,
    animalsCount: Int,
    modifier: Modifier = Modifier
) {
    val maxValue = maxOf(plantsCount, animalsCount).toFloat()
    val plantsProgress = remember { Animatable(0f) }
    val animalsProgress = remember { Animatable(0f) }
    
    LaunchedEffect(Unit) {
        plantsProgress.animateTo(
            targetValue = plantsCount / maxValue,
            animationSpec = tween(durationMillis = 1000, delayMillis = 100)
        )
        animalsProgress.animateTo(
            targetValue = animalsCount / maxValue,
            animationSpec = tween(durationMillis = 1000, delayMillis = 300)
        )
    }
    
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val barWidth = size.width / 4
        val spacing = size.width / 8
        val maxHeight = size.height - 40.dp.toPx()
        
        // Plants bar
        val plantsHeight = maxHeight * plantsProgress.value
        drawRoundRect(
            color = PlantGreen,
            topLeft = Offset(spacing, size.height - plantsHeight - 20.dp.toPx()),
            size = Size(barWidth, plantsHeight),
            cornerRadius = CornerRadius(12.dp.toPx())
        )
        
        // Animals bar
        val animalsHeight = maxHeight * animalsProgress.value
        drawRoundRect(
            color = AnimalBrown,
            topLeft = Offset(spacing * 2 + barWidth, size.height - animalsHeight - 20.dp.toPx()),
            size = Size(barWidth, animalsHeight),
            cornerRadius = CornerRadius(12.dp.toPx())
        )
    }
}

@Composable
fun LegendItem(
    label: String,
    color: Color,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(color)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}