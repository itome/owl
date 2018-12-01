package team.itome.owl.sample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

  private val mainViewModel: MainViewModel by lazy {
    ViewModelProviders.of(this).get(MainViewModel::class.java)
  }

  private val textCount: TextView by lazy { findViewById<TextView>(R.id.text_count) }
  private val buttonIncrement: Button by lazy { findViewById<Button>(R.id.button_increment) }
  private val buttonDecrement: Button by lazy { findViewById<Button>(R.id.button_decrement) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    buttonIncrement.setOnClickListener { }
    buttonDecrement.setOnClickListener { }
  }
}
