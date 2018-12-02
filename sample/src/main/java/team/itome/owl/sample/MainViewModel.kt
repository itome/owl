package team.itome.owl.sample

import team.itome.owl.OwlViewModel
import team.itome.owl.Result
import team.itome.owl.sample.MainAction.UpdateCountAction
import team.itome.owl.sample.MainIntent.DecrementIntent
import team.itome.owl.sample.MainIntent.IncrementIntent
import team.itome.owl.whenSuccess

class MainViewModel : OwlViewModel<MainIntent, MainAction, MainState>(MainState()) {

  override fun intentToAction(intent: MainIntent, state: MainState): MainAction = when (intent) {
    IncrementIntent -> UpdateCountAction(Result.just(state.count + 1))
    DecrementIntent -> UpdateCountAction(Result.just(state.count - 1))
  }

  override fun reducer(state: MainState, action: MainAction): MainState = when (action) {
    is UpdateCountAction -> action.whenSuccess<Int, MainState> { state.copy(count = it) }
  }
}