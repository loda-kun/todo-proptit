package net.proptit.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.proptit.compose.model.ActionEvent
import net.proptit.compose.model.Todo

class MainVimel : ViewModel() {

    data class MainViewState(
        var todos: MutableList<Todo> = mutableListOf()
    )

    private val _state = MutableStateFlow(MainViewState())
    val state = _state.asStateFlow()

    private var _state2 by mutableStateOf(MainViewState())


    fun add(text: String): Unit {
        // cach 1
        val todos = mutableListOf<Todo>().apply {
            addAll(_state.value.todos)
            add(Todo(text))
        }
        _state.update { state -> state.copy(todos = todos) }

        // cach 2
        _state2 = MainViewState()
    }

    fun remove(todo: Todo) {
        val newTodos = _state.value.todos.remove(todo)
    }

    fun onEvent(action: ActionEvent){
        when(action){
            is ActionEvent.Add -> TODO()
            is ActionEvent.Remove -> TODO()
        }
    }
}