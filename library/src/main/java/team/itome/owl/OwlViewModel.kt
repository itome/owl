package team.itome.owl

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class OwlViewModel<I : Intent, A : Action<*>, S : State>(private val initialState: S) : ViewModel() {

  abstract fun intentToAction(intent: I, state: S): A

  abstract fun reducer(state: S, action: A): S

  private val _state: MutableLiveData<S> = MutableLiveData()
  val state: LiveData<S> get() = _state

  init {
    _state.value = initialState
  }

  @MainThread
  fun dispatch(intent: I) {
    val state = _state.value ?: initialState
    val action = intentToAction(intent, state)
    processAction(action)
  }

  private fun processAction(action: A) {
    val state = _state.value ?: initialState
    val nextState = reducer(state, action)
    _state.value = nextState
  }
}