package com.example.taipeitravelapi.ui.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taipeitravelapi.LangDef
import com.example.taipeitravelapi.R
import com.example.taipeitravelapi.adapter.LangAdapter
import com.example.taipeitravelapi.ui.main.MainViewModel

class LanguageDialog : DialogFragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy {
        LangAdapter()
    }

    private val viewModel: MainViewModel by activityViewModels()

    override fun onResume() {
        super.onResume()
        if (dialog != null) {
            val metrics = resources.displayMetrics
            val width = metrics.widthPixels
            val height = metrics.heightPixels
            dialog!!.window!!.setLayout(
                Math.round(width * .75f),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setGravity(Gravity.CENTER)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = true
        return inflater.inflate(R.layout.dialog_lang, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnClicked(object : LangAdapter.IonClicked {
            override fun onClicked(langDef: LangDef, itemView: View) {
                viewModel.replaceLanguage(langDef.name)
                dismiss()
            }
        })
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }
}