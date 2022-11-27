package net.proptit.compose

sealed class Screen(
    open val route: String
) {
    object ListScreen : Screen("list")
    object DetailScreen : Screen("detail/{id}")
}