package team.itome.owl.sample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CounterViewModelTest {

    private lateinit var viewModel: CounterViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = CounterViewModel()
    }

    @Test
    fun testIncrementIntent() {
        val intent = CounterIntent.IncrementIntent
        val action = viewModel.intentToAction(intent, viewModel.currentState)
        assertEquals(CounterAction.UpdateCountAction(1), action)
    }

    @Test
    fun testDecrementIntent() {
        val intent = CounterIntent.DecrementIntent
        val action = viewModel.intentToAction(intent, viewModel.currentState)
        assertEquals(CounterAction.UpdateCountAction(-1), action)
    }

    @Test
    fun reducer() {
        val currentState = viewModel.currentState
        assertEquals(0, currentState.count)

        val action = CounterAction.UpdateCountAction(1)
        val nextState = viewModel.reducer(currentState, action)
        assertEquals(1, nextState.count)
    }
}