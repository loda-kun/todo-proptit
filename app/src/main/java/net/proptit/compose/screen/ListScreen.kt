package net.proptit.compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.proptit.compose.model.ActionEvent
import net.proptit.compose.model.Todo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    todos: List<Todo>,
    onEvent: (ActionEvent) -> Unit,
    onNavigateToDetail: (Int) -> Unit,
) {
    var text by remember { mutableStateOf("type something") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Checkbox(checked = false, onCheckedChange = {})
        TextField(value = text, onValueChange = { text = it })

        Button(onClick = {
            onEvent(ActionEvent.Add(text))
        }) {
            Text(text = "Add")
        }

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "todos",
            style = MaterialTheme.typography.titleLarge
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(todos) { index, todo ->

                val checked = todo.checked.collectAsState()

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(checked = checked.value, onCheckedChange = {
                        todo.toggle()
                    })
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                onNavigateToDetail(index)
                            },
                        text = todo.content
                    )
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "delete it",
                        modifier = Modifier.clickable {
                            onEvent(ActionEvent.Remove(todo))
                        }
                    )
                }
            }
        }
    }
}