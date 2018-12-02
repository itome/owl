package team.itome.owl.sample

import team.itome.owl.Event
import team.itome.owl.State

data class MainState(
    val count: Int = 0,
    val error: Event<Throwable>? = null
) : State