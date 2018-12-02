package team.itome.owl

@Suppress("UNUSED")
sealed class Result<out T> {
  data class Success<out T>(val value: T) : Result<T>()
  data class Failure(val error: Throwable) : Result<Nothing>()

  companion object {
    fun <T> just(data: T): Result<T> = Success(data)
    fun <T> raise(error: Throwable): Result<T> = Failure(error)

    inline operator fun <A> invoke(f: () -> A): Result<A> =
        try {
          Success(f())
        } catch (e: Throwable) {
          Failure(e)
        }
  }
}

