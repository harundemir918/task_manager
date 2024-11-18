package org.harundemir.taskmanager.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.harundemir.taskmanager.features.addNewTask.views.AddNewTaskScreen
import org.harundemir.taskmanager.features.home.views.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("addNewTask") {
            AddNewTaskScreen(navController)
        }
    }
}