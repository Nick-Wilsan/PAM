package com.example.tugas03

import androidx.compose.ui.Alignment
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Switch
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TaskScreen() }
    }
}
@Composable
fun TaskScreen(viewModel: TaskViewModel = viewModel()) {
// Read state. Recomposes when tasks change.
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()
    val showOnlyOpen by viewModel.showOnlyOpen.collectAsStateWithLifecycle()
    val displayedTasks = if (showOnlyOpen) tasks.filter { !it.done } else tasks
// Purely UI state: text being typed. This does NOT belong in the ViewModel.
    var newTitle by remember { mutableStateOf("") }
    Column(Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)) {
            Switch(
                checked = showOnlyOpen,
                onCheckedChange = { viewModel.toggleFilter(it) }
            )
            Text(
                text = "Show only open tasks",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newTitle,
                onValueChange = { newTitle = it },
                label = { Text("New task") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    viewModel.addTask(newTitle)
                    newTitle = ""
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Add")
            }
        }
        Column(Modifier.padding(top = 16.dp)) {
            displayedTasks.forEach { task ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { viewModel.toggleDone(task.id) }
                    )
                    Text(task.title)
                    Button(
                        onClick = { viewModel.deleteTask(task.id) },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
