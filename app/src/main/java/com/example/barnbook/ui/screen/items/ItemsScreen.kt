package com.example.barnbook.ui.screen.items

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.barnbook.R
import com.example.barnbook.ui.components.CategoryButton
import com.example.barnbook.ui.components.ItemCard
import com.example.barnbook.ui.theme.AnimalBrown
import com.example.barnbook.ui.theme.FreshGreen
import com.example.barnbook.ui.theme.PlantGreen

data class ProduceItem(
    val id: Int,
    val name: String,
    val icon: Int,
    val currentCount: Int,
    val unit: String,
    val isAnimal: Boolean
)

enum class ItemCategory {
    ANIMAL, PLANT
}

@Preview(showBackground = true)
@Composable
fun ItemsScreen() {
    var selectedCategory by remember { mutableStateOf(ItemCategory.ANIMAL) }
    
    val allItems = listOf(
        ProduceItem(1, "Eggs", R.drawable.activity, 24, "eggs", true),
        ProduceItem(2, "Milk", R.drawable.activity, 15, "liters", true),
        ProduceItem(3, "Wool", R.drawable.activity, 5, "kg", true),
        ProduceItem(4, "Honey", R.drawable.activity, 8, "jars", true),
        ProduceItem(5, "Tomatoes", R.drawable.items, 12, "kg", false),
        ProduceItem(6, "Carrots", R.drawable.items, 8, "kg", false),
        ProduceItem(7, "Lettuce", R.drawable.items, 20, "heads", false),
        ProduceItem(8, "Apples", R.drawable.items, 35, "kg", false)
    )
    
    val filteredItems = allItems.filter { item ->
        when (selectedCategory) {
            ItemCategory.ANIMAL -> item.isAnimal
            ItemCategory.PLANT -> !item.isAnimal
        }
    }
    
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Navigate to Add screen */ },
                containerColor = FreshGreen,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Item"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            
            // Header
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "My Items",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Manage your farm produce items",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            // Category Tabs
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CategoryButton(
                    label = "Animal",
                    isSelected = selectedCategory == ItemCategory.ANIMAL,
                    icon = R.drawable.activity,
                    selectedColor = AnimalBrown,
                    modifier = Modifier.weight(1f),
                    onClick = { selectedCategory = ItemCategory.ANIMAL }
                )
                CategoryButton(
                    label = "Plant",
                    isSelected = selectedCategory == ItemCategory.PLANT,
                    icon = R.drawable.items,
                    selectedColor = PlantGreen,
                    modifier = Modifier.weight(1f),
                    onClick = { selectedCategory = ItemCategory.PLANT }
                )
            }
            
            // Items Grid
            if (filteredItems.isEmpty()) {
                EmptyItemsState(selectedCategory)
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredItems) { item ->
                        ItemCard(
                            itemName = item.name,
                            icon = item.icon,
                            currentCount = item.currentCount.toString(),
                            unit = item.unit,
                            categoryColor = if (item.isAnimal) AnimalBrown else PlantGreen,
                            onClick = { /* Handle item click */ }
                        )
                    }
                    
                    // Add spacing at the bottom for FAB
                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyItemsState(category: ItemCategory) {
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
                .background(
                    if (category == ItemCategory.ANIMAL) 
                        AnimalBrown.copy(alpha = 0.2f) 
                    else 
                        PlantGreen.copy(alpha = 0.2f)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(
                    if (category == ItemCategory.ANIMAL) R.drawable.activity else R.drawable.items
                ),
                contentDescription = "No items",
                modifier = Modifier.size(40.dp),
                tint = if (category == ItemCategory.ANIMAL) AnimalBrown else PlantGreen
            )
        }
        
        Text(
            text = "No ${category.name.lowercase()} items yet",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Text(
            text = "Add your first ${category.name.lowercase()} item\nto start tracking harvest",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}
