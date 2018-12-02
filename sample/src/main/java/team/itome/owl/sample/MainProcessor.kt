package team.itome.owl.sample

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.itome.owl.Processor
import team.itome.owl.Result
import team.itome.owl.Result.Success
import team.itome.owl.sample.MainAction.*

class MainProcessor : Processor<MainAction>() {

  private fun processDelayedIncrementAction(action: DelayedIncrementAction) = launch {
    if (action.result !is Success) return@launch
    delay(1000)
    postAction(UpdateCountAction(Result.just(action.result.value)))
  }

  private fun processDelayedDecrementAction(action: DelayedDecrementAction) = launch {
    if (action.result !is Success) return@launch
    delay(1000)
    postAction(UpdateCountAction(Result.just(action.result.value)))
  }

  override fun processAction(action: MainAction) {
    when (action) {
      is DelayedIncrementAction -> processDelayedIncrementAction(action)
      is DelayedDecrementAction -> processDelayedDecrementAction(action)
    }
  }
}