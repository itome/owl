package team.itome.owl.sample

import team.itome.owl.OwlViewModel
import team.itome.owl.sample.CounterAction.*
import team.itome.owl.sample.CounterIntent.*

class CounterViewModel : OwlViewModel<CounterIntent, CounterAction, CounterState>(
    initialState = CounterState(),
    processor = CounterProcessor()
) {
    override fun intentToAction(intent: CounterIntent, state: CounterState): CounterAction = when (intent) {
        IncrementIntent -> UpdateCountAction(state.count + 1)
        DecrementIntent -> UpdateCountAction(state.count - 1)
        DelayedIncrementIntent -> DelayedIncrementAction(state.count + 1)
        DelayedDecrementIntent -> DelayedDecrementAction(state.count - 1)
    }

    override fun reducer(state: CounterState, action: CounterAction): CounterState = when (action) {
        is UpdateCountAction -> state.copy(count = action.count)
        else -> state
    }
}