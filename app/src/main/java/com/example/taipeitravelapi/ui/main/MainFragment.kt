package com.example.taipeitravelapi.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taipeitravelapi.R
import com.example.taipeitravelapi.adapter.MainAdapter
import com.example.taipeitravelapi.ui.customview.CustomTopBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var topBar: CustomTopBar

    private val adapter by lazy {
        MainAdapter(mutableListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        topBar = view.findViewById<CustomTopBar?>(R.id.topbar).apply {
            showLangBtn()
            setTitle(getString(R.string.app_name))
        }

        viewModel.attractionList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) return@observe
            adapter.replaceData(it)
        }
    }
}