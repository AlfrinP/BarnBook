package com.example.barnbook.ui.theme.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.barnbook.R
import com.example.barnbook.ui.components.ActivityCard
import com.example.barnbook.ui.components.DateHeader
import com.example.barnbook.ui.theme.AnimalBrown
import com.example.barnbook.ui.theme.PlantGreen

data class ActivityItemData(
    val name: String,
    val icon: Int,
    val dateTime: String,
    val count: String = "",
    val date: String,
    val isAnimal: Boolean = true
)

data class GroupedActivity(
    val date: String,
    val activities: List<ActivityItemData>
)

@Preview(showBackground = true)
@Composable
fun ActivityScreen() {
    val demoActivity = listOf(
        ActivityItemData(
            name = "Eggs",
            icon = R.drawable.activity,
            dateTime = "5:00 PM",
            count = "+8 eggs",
            date = "Today",
            isAnimal = true
        ),
        ActivityItemData(
            name = "Milk",
            icon = R.drawable.activity,
            dateTime = "3:30 PM",
            count = "+2 liters",
            date = "Today",
            isAnimal = true
        ),
        ActivityItemData(
            name = "Tomatoes",
            icon = R.drawable.items,
            dateTime = "2:15 PM",
            count = "+5 kg",
            date = "Today",
            isAnimal = false
        ),
        ActivityItemData(
            name = "Eggs",
            icon = R.drawable.activity,
            dateTime = "5:00 PM",
            count = "+3 eggs",
            date = "Yesterday",
            isAnimal = true
        ),
        ActivityItemData(
            name = "Carrots",
            icon = R.drawable.items,
            dateTime = "4:20 PM",
            count = "+3 kg",
            date = "Yesterday",
            isAnimal = false
        ),
        ActivityItemData(
            name = "Wool",
            icon = R.drawable.activity,
            dateTime = "10:00 AM",
            count = "+1.5 kg",
            date = "Yesterday",
            isAnimal = true
        )
    )
    
    // Group activities by date
    val groupedActivities = demoActivity.groupBy { it.date }.map { (date, activities) ->
        GroupedActivity(date, activities)
    }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Activity History",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Track all your farm produce entries",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        // Grouped activities by date
        groupedActivities.forEach { group ->
            item {
                DateHeader(date = group.date)
            }
            
            items(group.activities) { activity ->
                ActivityCard(
                    itemName = activity.name,
                    icon = activity.icon,
                    dateTime = activity.dateTime,
                    count = activity.count,
                    categoryColor = if (activity.isAnimal) AnimalBrown else PlantGreen,
                    onClick = { /* Handle click */ }
                )
            }
        }
        
        // Empty state if no activities
        if (demoActivity.isEmpty()) {
            item {
                EmptyActivityState()
            }
        }
        
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun EmptyActivityState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.activity),
                contentDescription = "No activities",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Text(
            text = "No activities yet",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Text(
            text = "Start tracking your farm produce\nto see your activity history here",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}