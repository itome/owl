package team.itome.owl.sample

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.itome.owl.OwlProcessor
import team.itome.owl.Result
import team.itome.owl.sample.MainAction.*
import kotlin.random.Random

class MainProcessor : OwlProcessor<MainAction>() {

  private fun processDelayedIncrementAction(action: DelayedIncrementAction) = launch {
    delay(1000)
    postAction(UpdateCountAction(Result {
      when {
        Random.nextInt(2) == 0 -> action.count
        else -> throw RuntimeException()
      }
    }))
  }

  private fun processDelayedDecrementAction(action: DelayedDecrementAction) = launch {
    delay(1000)
    postAction(UpdateCountAction(Result { action.count }))
  }

  override fun processAction(action: MainAction) {
    when (action) {
      is DelayedIncrementAction -> processDelayedIncrementAction(action)
      is DelayedDecrementAction -> processDelayedDecrementAction(action)
    }
  }
}