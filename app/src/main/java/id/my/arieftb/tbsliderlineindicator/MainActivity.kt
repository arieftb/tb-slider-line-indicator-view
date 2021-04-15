package id.my.arieftb.tbsliderlineindicator

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import id.my.arieftb.tbsliderlineindicator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)

        adapter = SliderAdapter().apply {
            listItem = getData()
        }

        binding.slider.adapter = adapter
        binding.indicator.setViewPager2(binding.slider)
    }

    private fun getData(): List<String> {
        val listItem = emptyList<String>().toMutableList()

        for (i in 0 until 5) {
            listItem.add("")
        }

        return listItem
    }
}