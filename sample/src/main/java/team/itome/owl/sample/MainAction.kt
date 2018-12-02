package team.itome.owl.sample

import team.itome.owl.Action
import team.itome.owl.Result

sealed class MainAction<T> : Action<T>() {
  data class UpdateCountAction(override val data: Result<Int>) : MainAction<Int>()
}