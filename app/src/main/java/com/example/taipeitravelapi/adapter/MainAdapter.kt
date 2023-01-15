package com.example.taipeitravelapi.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.taipeitravelapi.R
import com.example.taipeitravelapi.model.ItemAttraction

class MainAdapter(private var list: MutableList<ItemAttraction>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    public fun replaceData(newList: List<ItemAttraction>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: AppCompatImageView? = null
        var title: AppCompatTextView? = null
        var content: AppCompatTextView? = null
        var arrowBtn: ImageButton? = null

        init {
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
            content = itemView.findViewById(R.id.content)
            arrowBtn = itemView.findViewById(R.id.arrowBtn)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_attractions, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = list[position]
        holder.title?.text = data.name
        holder.content?.text = data.introduction
        holder.image?.let {
            Glide.with(holder.itemView.context)
                .load(if (data.images.isNullOrEmpty()) R.drawable.ic_image else data.images.first().src)
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .thumbnail(.1f)
                .into(it)

        }
        holder.arrowBtn?.setOnClickListener(clickEvent(list[position]))
    }

    override fun getItemCount() = if (list.isEmpty()) 0 else list.size

    override fun getItemViewType(position: Int) = R.layout.item_attractions

    private fun clickEvent(itemAttraction: ItemAttraction) = View.OnClickListener {
        val bundle = Bundle().apply {
            putParcelable("attraction", itemAttraction)
        }
        Navigation.findNavController(it).navigate(R.id.action_main_to_detail, bundle)
    }
}