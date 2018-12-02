package team.itome.owl

import team.itome.owl.Result.Failure
import team.itome.owl.Result.Success

abstract class Action<T> {
  abstract val data: Result<T>

  fun <S> whenSuccess(callback: (T) -> S): S = when (data) {
    is Success -> callback((data as Success).value)
    is Failure -> throw IllegalStateException("Action has Failure type of Result. You should handle Failure case in reducer")
  }
}
