package team.itome.owl

import team.itome.owl.Result.Failure
import team.itome.owl.Result.Success

@Suppress("UNCHECKED_CAST")
fun <T, S> Action.whenSuccess(callback: (T) -> S): S = when (result) {
  is Success -> callback((result as Success).value as T)
  is Failure -> throw IllegalStateException("Action has Failure type of Result. You should handle Failure case in reducer")
}
