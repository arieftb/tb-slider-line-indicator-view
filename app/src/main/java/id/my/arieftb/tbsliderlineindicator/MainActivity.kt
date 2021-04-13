package id.my.arieftb.tbsliderlineindicator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.Slide
import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import id.my.arieftb.tbsliderlineindicator.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SliderAdapter
    private var currentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)

        adapter = SliderAdapter().apply {
            listItem = getData()
        }

        binding.slider.adapter = adapter
        binding.indicator.setViewPager2(binding.slider)

        setAutoSlide()
    }

    private fun setAutoSlide() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post {
                    if (currentPosition == adapter.itemCount) {
                        currentPosition = 0
                    }

                    binding.slider.setCurrentItem(currentPosition++, true)
                }
            }
        }, 5000, 5000)
    }

    private fun getData(): List<String> {
        val listItem = emptyList<String>().toMutableList()

        for (i in 0 until 5) {
            listItem.add("")
        }

        return listItem
    }
}