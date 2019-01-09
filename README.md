[![Download](https://api.bintray.com/packages/itome-team/maven/owl/images/download.svg) ](https://bintray.com/itome-team/maven/owl/_latestVersion)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# Owl

State management system for Android Application, inspired by Redux and MVI.

## Documentation

This library provides
- Redux like state management.
- Easy coroutine integration.
- Lifecycle aware view state stream with Android Architecture Component.

This is what it looks like:
```kotlin
sealed class CounterIntent : Intent {
  object IncrementIntent : CounterIntent()
  object DecrementIntent : CounterIntent()
}

sealed class CounterAction : Action {
    data class UpdateCountAction(val count: Int) : CounterAction()
}

data class CounterState(val count: Int = 0) : State

class CounterViewModel : OwlViewModel<CounterIntent, CounterAction, CounterState>(initialState = CounterState()) {
    override fun intentToAction(intent: CounterIntent, state: CounterState): CounterAction = when (intent) {
        IncrementIntent -> UpdateCountAction(state.count + 1)
        DecrementIntent -> UpdateCountAction(state.count - 1)
    }

    override fun reducer(state: CounterState, action: CounterAction): CounterState = when (action) {
        is UpdateCountAction -> state.copy(count = action.count)
    }
}

class MainActivity : AppCompatActivity() {

    private val counterViewModel: CounterViewModel by lazy {
        ViewModelProviders.of(this).get(CounterViewModel::class.java)
    }

    private val textCount: TextView by lazy { findViewById<TextView>(R.id.text_count) }
    private val buttonIncrement: Button by lazy { findViewById<Button>(R.id.button_increment) }
    private val buttonDecrement: Button by lazy { findViewById<Button>(R.id.button_decrement) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonIncrement.setOnClickListener {
            counterViewModel.dispatch(IncrementIntent)
        }
        buttonDecrement.setOnClickListener {
            counterViewModel.dispatch(DecrementIntent)
        }

        counterViewModel.state.observe(this, Observer { state ->
            textCount.text = "count: ${state.count}"
        })
    }
}

```

## How it works
The start point of changing state is `OwlViewModel#dispatch`. When we dispatch `Intent`, The intent is converted to `Action` in `OwlViewModel#intentToAction` and reach to `OwlViewModel#reducer` where state actually changed.

All state change is notified to `OwlViewModel#state` livedata, So we can observe it.

## Rules
Owl has simple rule for its classes.
### `Intent`
`Intent` is the sealed class that indicates how we want to change the `State`. We should not consider current `State` when we dispatch `Intent`.
### `Action`
`Action` is the sealed class that have the data we actually want to apply to `State`. We can access to `Intent` dispatched and current `State` when we create `Action`.
### `State`
`State` is just a data class that contains view state. We should not write any logic in it.

## Async Programing with Kotlin Coroutine.
Owl has `Processor` class for coroutine. To connect processor with ViewModel, pass `Processor` to `OwlViewModel` constructor.
```kotlin
class CounterViewModel : OwlViewModel<CounterIntent, CounterAction, CounterState>(
    initialState = CounterState(),
    processor = CounterProcessor()
) {
```
Then `OwlViewModel` notify `Processor` when `Action` dispatched. Processor itself is coroutine. So we can launch coroutine to do async programing. After we finish, we can notify `Action` to `OwlViewModel` by calling `Processor#put`.

```kotlin
class CounterProcessor : Processor<CounterAction>() {
    private fun processDelayedIncrementAction(action: DelayedIncrementAction) = launch {
        delay(1000)
        put(UpdateCountAction(action.count))
    }

    override fun processAction(action: CounterAction) {
        when (action) {
            is DelayedIncrementAction -> processDelayedIncrementAction(action)
        }
    }
}
```

Because `OwlViewModel` is fully independent from `Processor`, we don't have to consider about async job status in `OwlViewModel`. All `OwlViewModel` have to do is processing `Action` in `reducer` synchronously.
