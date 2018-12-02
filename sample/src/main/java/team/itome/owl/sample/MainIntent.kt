package team.itome.owl.sample

import team.itome.owl.Intent

sealed class MainIntent : Intent {
  object IncrementIntent : MainIntent()
  object DecrementIntent : MainIntent()

  object DelayedIncrementIntent: MainIntent()
  object DelayedDecrementIntent: MainIntent()
}