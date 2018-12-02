package team.itome.owl.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import team.itome.owl.sample.MainIntent.DecrementIntent
import team.itome.owl.sample.MainIntent.IncrementIntent

class MainActivity : AppCompatActivity() {

  private val mainViewModel: MainViewModel by lazy {
    ViewModelProviders.of(this).get(MainViewModel::class.java)
  }

  private val textCount: TextView by lazy { findViewById<TextView>(R.id.text_count) }
  private val buttonIncrement: Button by lazy { findViewById<Button>(R.id.button_increment) }
  private val buttonDecrement: Button by lazy { findViewById<Button>(R.id.button_decrement) }

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    buttonIncrement.setOnClickListener {
      mainViewModel.dispatch(IncrementIntent)
    }
    buttonDecrement.setOnClickListener {
      mainViewModel.dispatch(DecrementIntent)
    }

    mainViewModel.state.observe(this, Observer { state ->
      textCount.text = "count: ${state.count}"
    })
  }
}
