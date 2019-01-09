package team.itome.owl.sample

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.itome.owl.Processor
import team.itome.owl.sample.CounterAction.*

class CounterProcessor : Processor<CounterAction>() {

    private fun processDelayedIncrementAction(action: DelayedIncrementAction) = launch {
        delay(1000)
        put(UpdateCountAction(action.count))
    }

    private fun processDelayedDecrementAction(action: DelayedDecrementAction) = launch {
        delay(1000)
        put(UpdateCountAction(action.count))
    }

    override fun processAction(action: CounterAction) {
        when (action) {
            is DelayedIncrementAction -> processDelayedIncrementAction(action)
            is DelayedDecrementAction -> processDelayedDecrementAction(action)
        }
    }
}