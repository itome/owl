package team.itome.owl.sample

import team.itome.owl.Intent

sealed class CounterIntent : Intent {
  object IncrementIntent : CounterIntent()
  object DecrementIntent : CounterIntent()

  object DelayedIncrementIntent: CounterIntent()
  object DelayedDecrementIntent: CounterIntent()
}