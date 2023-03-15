package com.aps.compose_starter.demoui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

/*setContent {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                item = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = "home",
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(
                        name = "Chat",
                        route = "chat",
                        icon = Icons.Default.Notifications,
                        badgeCount = 23
                    ),
                    BottomNavItem(
                        name = "Setting",
                        route = "setting",
                        icon = Icons.Default.Settings,
                        badgeCount = 214
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                } )
        }
    ) {
        com.aps.compose_starter.demoui.Navigation(navHostController = navController)
    }
} */

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }

        composable("chat") {
            ChatScreen()
        }
        composable("setting") {
            SettingScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen")
    }
}

@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = "Chat Screen")
    }
}

@Composable
fun SettingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = "Setting Screen")
    }
}

@Composable
fun BottomNavigationBar(
    item: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        item.forEach { data ->
            val selected = data.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected, onClick = {
                    onItemClick(data)
                }, selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (data.badgeCount > 0) {

                            BadgedBox(badge = {
                                Badge(content = {Text(text = data.badgeCount.toString())}
                                , backgroundColor = Color.Red,
                                contentColor = Color.White)
                               // Text(text = data.badgeCount.toString())
                            }) {
                                Icon(imageVector = data.icon, contentDescription = data.name)
                            }
                        } else {
                            Icon(imageVector = data.icon, contentDescription = data.name)
                        }

                        if (selected) {
                            Text(text = data.name, textAlign = TextAlign.Center, fontSize = 10.sp)
                        }
                    }
                })
        }
    }
}