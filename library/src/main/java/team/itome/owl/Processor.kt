package team.itome.owl

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class Processor<A : Action>(
    override val coroutineContext: CoroutineContext = Dispatchers.Default
) : CoroutineScope {

    protected fun put(action: A) = launch(Dispatchers.Main) {
        postActionCallback?.invoke(action)
    }

    abstract fun processAction(action: A)

    private var postActionCallback: ((action: A) -> Unit)? = null

    fun setPostActionCallback(callback: (action: A) -> Unit) {
        this.postActionCallback = callback
    }

    fun onCleared() {
        coroutineContext.cancel()
    }
}