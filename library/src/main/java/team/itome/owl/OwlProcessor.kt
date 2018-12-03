package team.itome.owl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class OwlProcessor<A : OwlAction> : CoroutineScope {

  protected fun postAction(action: A) = launch(Dispatchers.Main) {
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