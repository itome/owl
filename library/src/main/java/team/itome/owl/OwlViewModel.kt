package team.itome.owl

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class OwlViewModel<I : Intent, A : Action, S : State>(
    initialState: S,
    private val processor: Processor<A> = NothingProcessor()
) : ViewModel() {

    abstract fun intentToAction(intent: I, state: S): A

    abstract fun reducer(state: S, action: A): S

    val state: LiveData<S> get() = _state

    val currentState: S get() = _state.value!!

    @MainThread
    fun dispatch(intent: I) {
        val state = _state.value!!
        val action = intentToAction(intent, state)
        setState(action)
        processor.processAction(action)
    }

    private val _state: MutableLiveData<S> = MutableLiveData()

    init {
        processor.setPostActionCallback(::setState)
        _state.value = initialState
    }

    @MainThread
    private fun setState(action: A) {
        val nextState = reducer(_state.value!!, action)
        _state.value = nextState
    }

    override fun onCleared() {
        processor.onCleared()
    }

    class NothingProcessor<A : Action> : Processor<A>() {
        override fun processAction(action: A) {}
    }
}