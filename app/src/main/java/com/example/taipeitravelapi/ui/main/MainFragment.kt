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
import com.example.taipeitravelapi.databinding.FragmentMainBinding
import com.example.taipeitravelapi.ui.customview.CustomTopBar
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference


@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private var binding: FragmentMainBinding? = null

    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MainAdapter(mutableListOf())
        binding?.let {
            it.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            it.recyclerView.adapter = adapter
            it.topbar.root.showLangBtn()
            it.topbar.root.setTitle(getString(R.string.app_name))
        }

        viewModel.attractionList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) return@observe
            adapter?.replaceData(it)
        }
    }

    override fun onDestroyView() {
        binding = null
        adapter = null
        super.onDestroyView()
    }
}