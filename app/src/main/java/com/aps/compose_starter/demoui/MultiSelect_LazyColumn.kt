package com.aps.compose_starter.demoui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MultiSelectLazyColumn(){
    var lazyItems by remember {
        mutableStateOf(
            (1..20).map {
                LazyItem(title = "Item $it",
                isSelected = false)
            }
        )
    }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ){
        items(lazyItems.size){ i ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    lazyItems = lazyItems.mapIndexed { index, lazyItem ->
                        if(i==index){
                            lazyItem.copy(isSelected = !lazyItem.isSelected)
                        }else{
                            lazyItem
                        }
                    }
                }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
                Text(text = lazyItems[i].title)
                if(lazyItems[i].isSelected){
                    Icon(imageVector = Icons.Default.Check, contentDescription = "selected",
                    tint = Color.Green,
                        modifier = Modifier.size(20.dp)
                    )
                }

            }
        }
    }
}