package team.itome.owl

class Event<T>(private val data: T) {
    private var consumed: Boolean = false

    fun consume(action: (data: T) -> Unit) {
        if (!consumed) {
            action(data)
            consumed = true
        }
    }
}
