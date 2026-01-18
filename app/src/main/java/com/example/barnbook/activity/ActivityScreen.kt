package com.example.barnbook.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.barnbook.R


data class ActivityItemData(
    val name: String,
    val icon: Int,
    val dateTime: String
)

@Preview
@Composable
fun ActivityScreen() {
    val demoActivity = listOf<ActivityItemData>(
        ActivityItemData(
            "Eggs",
            R.drawable.activity,
            "Today · 5:00PM"
        ),
        ActivityItemData(
            "Eggs",
            R.drawable.activity,
            "Today · 5:00PM"
        ),
        ActivityItemData(
            "Eggs",
            R.drawable.activity,
            "Today · 5:00PM"
        ), ActivityItemData(
            "Eggs",
            R.drawable.activity,
            "Today · 5:00PM"
        ), ActivityItemData(
            "Eggs",
            R.drawable.activity,
            "Today · 5:00PM"
        ), ActivityItemData(
            "Eggs",
            R.drawable.activity,
            "Today · 5:00PM"
        )
    )
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item(demoActivity.size) {
            demoActivity.forEach { activity ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(5.dp))
                        .padding(5.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            2.dp,
                            Alignment.Start
                        )
                    ) {
                        Icon(
                            painter = painterResource(activity.icon),
                            contentDescription = activity.name
                        )
                        Text(text = activity.name)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = activity.dateTime, fontSize = 10.sp)
                    }
                }
            }
        }
    }
}