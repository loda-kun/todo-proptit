package net.proptit.compose.model

sealed class ActionEvent {
    data class Add(val text: String) : ActionEvent()
    data class Remove(val todo: Todo) : ActionEvent()
}