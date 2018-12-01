package team.itome.owl.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

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
