package com.example.taipeitravelapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.taipeitravelapi.R
import com.example.taipeitravelapi.model.Image

class BannerAdapter(private val list: MutableList<Image>) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {


    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var imageView:AppCompatImageView
        init {
            imageView = itemView.findViewById(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerAdapter.BannerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_banner, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.imageView.let {
            Glide.with(holder.itemView.context)
                .load(list[position].src)
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .thumbnail(.1f)
                .into(it)
        }
    }

    override fun getItemCount() = if (list.isEmpty()) 0 else list.size

    override fun getItemViewType(position: Int) = R.layout.item_banner

    fun refreshBannerData(newList:List<Image>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}