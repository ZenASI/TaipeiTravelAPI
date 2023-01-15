package com.example.taipeitravelapi.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import com.example.taipeitravelapi.R

class CustomTopBar(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private lateinit var titleText: AppCompatTextView
    private lateinit var back: AppCompatImageButton
    private lateinit var lang: AppCompatImageButton

    override fun onFinishInflate() {
        super.onFinishInflate()

        back = findViewById(R.id.backBtn)
        titleText = findViewById(R.id.topTitle)
        lang = findViewById(R.id.langBtn)

        back.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
        lang.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.langFragmentDialog)
        }
    }

    public fun setTitle(title: String?) = run { titleText.text = title }

    public fun showBackBtn() = run { back.visibility = View.VISIBLE }

    public fun showLangBtn() = run { lang.visibility = View.VISIBLE }
}