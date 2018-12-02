package team.itome.owl

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class OwlViewModel<I : Intent, A : Action, S : State>(private val initialState: S) : ViewModel() {

  abstract fun intentToAction(intent: I, state: S): A

  abstract fun reducer(state: S, action: A): S

  val state: LiveData<S> get() = _state

  val currentState: S get() = _state.value!!

  @MainThread
  fun dispatch(intent: I) {
    val state = _state.value!!
    val action = intentToAction(intent, state)
    processAction(action)
  }

  private val _state: MutableLiveData<S> = MutableLiveData()

  init {
    _state.value = initialState
  }

  private fun processAction(action: A) {
    val state = _state.value ?: initialState
    val nextState = reducer(state, action)
    _state.value = nextState
  }
}