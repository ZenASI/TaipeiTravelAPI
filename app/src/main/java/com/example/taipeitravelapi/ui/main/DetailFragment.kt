package com.example.taipeitravelapi.ui.main

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.taipeitravelapi.R
import com.example.taipeitravelapi.model.ItemAttraction
import com.example.taipeitravelapi.ui.customview.CustomBanner
import com.example.taipeitravelapi.ui.customview.CustomTopBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var title: AppCompatTextView
    private lateinit var content: AppCompatTextView
    private lateinit var link: AppCompatTextView
    private lateinit var topBar: CustomTopBar
    private lateinit var banner: CustomBanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        banner = view.findViewById(R.id.detailBanner)
        title = view.findViewById(R.id.detailTitle)
        content = view.findViewById(R.id.detailContent)
        link = view.findViewById(R.id.detailLink)

        topBar = view.findViewById<CustomTopBar?>(R.id.topbar).apply {
            showBackBtn()
        }

        arguments?.let { bundle ->
            val item = bundle.getParcelable("attraction", ItemAttraction::class.java)
            title.text = item?.name
            topBar.setTitle(item?.name)
            content.text = item?.introduction
            link.text = item?.url

            item?.images?.let {
                banner.showBanner()
                banner.setBannerData(it)
            }
        }
    }

    override fun onDestroyView() {
        banner.release()
        super.onDestroyView()
    }
}