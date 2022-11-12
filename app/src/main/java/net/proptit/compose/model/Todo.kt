package net.proptit.compose.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Todo(
    val content: String,
    private val _checked: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val checked: StateFlow<Boolean> = _checked,
) {
    fun toggle() {
        _checked.value = !_checked.value
    }
}