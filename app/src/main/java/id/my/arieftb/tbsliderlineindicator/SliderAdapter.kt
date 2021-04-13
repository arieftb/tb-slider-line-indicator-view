package id.my.arieftb.tbsliderlineindicator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.my.arieftb.tbsliderlineindicator.databinding.ItemSliderSampleBinding

class SliderAdapter : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    var listItem: List<String> = emptyList()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(ItemSliderSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false).root)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = listItem.size

    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}