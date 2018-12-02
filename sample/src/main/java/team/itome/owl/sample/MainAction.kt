package team.itome.owl.sample

import team.itome.owl.Action
import team.itome.owl.Result

sealed class MainAction : Action {
  data class UpdateCountAction(override val result: Result<Int>) : MainAction()
}