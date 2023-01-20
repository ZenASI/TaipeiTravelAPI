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
import com.example.taipeitravelapi.databinding.FragmentDetailBinding
import com.example.taipeitravelapi.model.ItemAttraction
import com.example.taipeitravelapi.ui.customview.CustomBanner
import com.example.taipeitravelapi.ui.customview.CustomTopBar
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private var binding: FragmentDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            it.detailBanner.showBanner()
            it.topbar.root.showBackBtn()
        }

        arguments?.let { bundle ->
            val item = if (Build.VERSION.SDK_INT >= 33) bundle.getParcelable(
                "attraction",
                ItemAttraction::class.java
            ) else bundle.getParcelable("attraction")
            binding?.let {
                it.detailTitle.text = item?.name
                it.detailContent.text = item?.introduction
                it.detailLink.text = item?.url
                it.topbar.root.setTitle(item?.name)
            }

            item?.images?.let {
                binding?.detailBanner?.showBanner()
                binding?.detailBanner?.setBannerData(it)
            }
        }
    }

    override fun onDestroyView() {
        binding?.detailBanner?.release()
        binding = null
        super.onDestroyView()
    }
}