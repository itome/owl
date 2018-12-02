package team.itome.owl.sample

import team.itome.owl.Action
import team.itome.owl.Result

sealed class MainAction : Action {
  data class UpdateCountAction(val result: Result<Int>) : MainAction()

  data class DelayedIncrementAction(val count: Int) : MainAction()
  data class DelayedDecrementAction(val count: Int) : MainAction()
}