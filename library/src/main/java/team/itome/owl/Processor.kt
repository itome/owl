package team.itome.owl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class Processor<A : Action> : CoroutineScope {

  protected fun postAction(action: A) {
    postActionCallback?.invoke(action)
  }

  abstract fun processAction(action: A)

  private val rootParent = Job()

  private var postActionCallback: ((action: A) -> Unit)? = null

  override val coroutineContext: CoroutineContext get() = rootParent

  fun setPostActionCallback(callback: (action: A) -> Unit) {
    this.postActionCallback = callback
  }

  fun onCleared() {
    rootParent.cancel()
  }
}