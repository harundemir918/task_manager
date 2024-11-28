package org.harundemir.taskmanager.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.harundemir.taskmanager.features.addNewTask.views.AddNewTaskScreen
import org.harundemir.taskmanager.features.home.views.HomeScreen
import org.harundemir.taskmanager.features.taskDetail.views.TaskDetailScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavigation(startDestination: String = "home") {
    val navController = rememberNavController()
    NavHost(navController, startDestination = startDestination) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("addNewTask") {
            AddNewTaskScreen(navController)
        }
        composable("taskDetail/{index}", arguments = listOf(navArgument("index") {
            type = NavType.IntType
        })) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            TaskDetailScreen(index, navController)
        }
    }
}