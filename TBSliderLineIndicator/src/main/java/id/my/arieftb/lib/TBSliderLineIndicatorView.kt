package id.my.arieftb.lib

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import androidx.core.view.forEachIndexed
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class TBSliderLineIndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val linearLayoutParent = LinearLayout(context)
    private val indicatorList = ArrayList<ImageView>()
    private var count: Int = 0

    init {
        linearLayoutParent.orientation = HORIZONTAL
        addView(linearLayoutParent, MATCH_PARENT, WRAP_CONTENT)

        if (isInEditMode) {
            addIndicators(5)
        }
    }

    private fun addIndicators(count: Int) {
        if (count > 0) {
            this.count = count
            for (i in 0 until count) {
                addIndicator()
            }
            setSelectedIndicator(0)
        }
    }

    private fun addIndicator() {
        val indicator = buildIndicator()

        indicatorList.add(indicator.findViewById(R.id.imageLineIndicator) as ImageView)
        linearLayoutParent.addView(indicator)
    }

    private fun setSelectedIndicator(index: Int) {
        linearLayoutParent.getChildAt(index).findViewById<ImageView>(R.id.imageLineIndicator)
            .setBackgroundResource(R.drawable.background_line_indicator_selected)

        linearLayoutParent.forEachIndexed { position, view ->
            if (position > index) {
                view.findViewById<ImageView>(R.id.imageLineIndicator)
                    .setBackgroundResource(R.drawable.background_line_indicator_unselected)
            }
        }
    }


    private fun buildIndicator(): ViewGroup {
        val indicator = LayoutInflater.from(context)
            .inflate(R.layout.layout_line_indicator, this, false) as LinearLayout
        indicator.layoutDirection = View.LAYOUT_DIRECTION_LTR

        val indicatorView = indicator.findViewById<ImageView>(R.id.imageLineIndicator)
        indicatorView.setBackgroundResource(R.drawable.background_line_indicator_unselected)

        val param = indicatorView.layoutParams
        param.width = MATCH_PARENT
        param.height = 2

        indicator.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT, 1f).apply {
            marginEnd = 2
            marginStart = 2
        }

        return indicator
    }

    fun setViewPager2(viewPager: ViewPager2?) {
        if (viewPager?.adapter == null) {
            throw IllegalStateException("You have to set adapter to your ViewPager2 first")
        }

        viewPager.adapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                addIndicators(viewPager.adapter?.itemCount!!)
            }
        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setSelectedIndicator(position)
            }
        })

        addIndicators(viewPager.adapter?.itemCount!!)
    }
}