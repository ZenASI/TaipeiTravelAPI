package com.example.taipeitravelapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taipeitravelapi.LangDef
import com.example.taipeitravelapi.R

open class LangAdapter(private var list: List<LangDef> = LangDef.values().toList()) :
    RecyclerView.Adapter<LangAdapter.LangViewHolder>() {

    interface IonClicked {
        fun onClicked(langDef: LangDef, itemView: View)
    }

    var mIonClicked: IonClicked? = null

    open fun setOnClicked(onClickListener: IonClicked) = kotlin.run { mIonClicked = onClickListener }

    class LangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var langTitle: AppCompatTextView? = null
        init {
            langTitle = itemView.findViewById(R.id.langText)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LangViewHolder {
        return LangViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lang, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LangViewHolder, position: Int) {
        holder.langTitle?.text = list[position].name
        holder.langTitle?.setOnClickListener {
            mIonClicked?.onClicked(list[position], holder.itemView)
        }
    }


    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int) = R.layout.item_lang
}