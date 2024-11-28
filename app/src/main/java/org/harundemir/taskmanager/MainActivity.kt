package org.harundemir.taskmanager

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import org.harundemir.taskmanager.core.navigation.AppNavigation
import org.harundemir.taskmanager.core.services.TaskReminderNotification
import org.harundemir.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TaskReminderNotification(context = this).createNotificationChannel()
        requestNotificationPermission()
        val startDestination = intent?.getStringExtra("destination") ?: "home"
        enableEdgeToEdge()
        setContent {
            TaskManagerTheme {
                AppNavigation(startDestination)
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this, POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    showToast("Notification permission already granted!")
                }

                shouldShowRequestPermissionRationale(POST_NOTIFICATIONS) -> {
                    showToast("Notification permission is required for better experience!")
                }

                else -> {
                    requestPermission.launch(POST_NOTIFICATIONS)
                }
            }
        } else {
            showToast("Notification permissions not required for this Android version!")
        }
    }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                showToast("Notification permission granted!")
            } else {
                showToast("Notification permission denied!")
            }
        }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}