package team.itome.owl.sample

import io.mockk.called
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class CounterProcessorTest {

    private lateinit var processor: CounterProcessor
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    private val mockCallback: (CounterAction) -> Unit = spyk()

    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        processor = CounterProcessor(dispatcher)
        processor.setPostActionCallback(mockCallback)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun processAction() {
        processor.processAction(CounterAction.DelayedIncrementAction(1))
        verify { mockCallback.invoke(CounterAction.UpdateCountAction(1)) wasNot called }
        dispatcher.advanceTimeBy(1000)
        verify { mockCallback.invoke(CounterAction.UpdateCountAction(1)) }
    }
}