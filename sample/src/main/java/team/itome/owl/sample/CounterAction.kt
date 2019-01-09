package team.itome.owl.sample

import team.itome.owl.Action

sealed class CounterAction : Action {
    data class UpdateCountAction(val count: Int) : CounterAction()

    data class DelayedIncrementAction(val count: Int) : CounterAction()
    data class DelayedDecrementAction(val count: Int) : CounterAction()
}