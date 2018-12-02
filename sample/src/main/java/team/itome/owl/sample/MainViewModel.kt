package team.itome.owl.sample

import team.itome.owl.OwlViewModel
import team.itome.owl.Processor
import team.itome.owl.Result
import team.itome.owl.sample.MainAction.*
import team.itome.owl.sample.MainIntent.*
import team.itome.owl.whenSuccess

class MainViewModel : OwlViewModel<MainIntent, MainAction, MainState>(MainState()) {

  override val processor: Processor<MainAction>? = MainProcessor()

  override fun intentToAction(intent: MainIntent, state: MainState): MainAction = when (intent) {
    IncrementIntent -> UpdateCountAction(Result.just(state.count + 1))
    DecrementIntent -> UpdateCountAction(Result.just(state.count - 1))
    DelayedIncrementIntent -> DelayedIncrementAction(Result.just(state.count + 1))
    DelayedDecrementIntent -> DelayedDecrementAction(Result.just(state.count - 1))
  }

  override fun reducer(state: MainState, action: MainAction): MainState = when (action) {
    is UpdateCountAction -> action.whenSuccess { count: Int -> state.copy(count = count) }
    else -> state
  }
}