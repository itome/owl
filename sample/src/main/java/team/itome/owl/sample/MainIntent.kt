package team.itome.owl.sample

import team.itome.owl.OwlIntent

sealed class MainIntent : OwlIntent {
  object IncrementIntent : MainIntent()
  object DecrementIntent : MainIntent()

  object DelayedIncrementIntent: MainIntent()
  object DelayedDecrementIntent: MainIntent()
}