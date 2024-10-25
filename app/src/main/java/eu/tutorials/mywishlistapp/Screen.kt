package eu.tutorials.mywishlistapp

sealed class Screen(val route:String) {
    object HomeScreen: Screen("home_screen")
    object AddScreen: Screen("add_screen")
    object LoginScreen: Screen("login_screen")
}