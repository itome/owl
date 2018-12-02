package team.itome.owl.sample

import team.itome.owl.Event
import team.itome.owl.OwlProcessor
import team.itome.owl.OwlViewModel
import team.itome.owl.Result
import team.itome.owl.Result.Failure
import team.itome.owl.Result.Success
import team.itome.owl.sample.MainAction.*
import team.itome.owl.sample.MainIntent.*

class MainViewModel : OwlViewModel<MainIntent, MainAction, MainState>(MainState()) {

  override val processor: OwlProcessor<MainAction> = MainProcessor()

  override fun intentToAction(intent: MainIntent, state: MainState): MainAction = when (intent) {
    IncrementIntent -> UpdateCountAction(Result.just(state.count + 1))
    DecrementIntent -> UpdateCountAction(Result.just(state.count - 1))
    DelayedIncrementIntent -> DelayedIncrementAction(state.count + 1)
    DelayedDecrementIntent -> DelayedDecrementAction(state.count - 1)
  }

  override fun reducer(state: MainState, action: MainAction): MainState = when (action) {
    is UpdateCountAction -> when (action.result) {
      is Success -> state.copy(count = action.result.value)
      is Failure -> state.copy(error = Event(action.result.error))
    }
    else -> state
  }
}