package two.screens.woom.core

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object NullException: Failure()
}