package team.itome.owl.sample

import team.itome.owl.OwlAction
import team.itome.owl.Result

sealed class MainAction : OwlAction {
  data class UpdateCountAction(val result: Result<Int>) : MainAction()

  data class DelayedIncrementAction(val count: Int) : MainAction()
  data class DelayedDecrementAction(val count: Int) : MainAction()
}