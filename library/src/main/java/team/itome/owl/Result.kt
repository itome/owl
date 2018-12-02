package team.itome.owl

@Suppress("UNUSED")
sealed class Result<T> {
  data class Success<T>(val value: T) : Result<T>()
  data class Failure<T>(val error: Throwable) : Result<T>()

  companion object {
    fun <T> just(data: T): Result<T> = Success(data)
    fun <T> raise(error: Throwable): Result<T> = Failure(error)
  }
}

