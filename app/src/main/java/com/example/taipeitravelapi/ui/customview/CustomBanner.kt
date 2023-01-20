package com.example.taipeitravelapi.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.taipeitravelapi.adapter.BannerAdapter
import com.example.taipeitravelapi.model.Image
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class CustomBanner(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    private val bannerSnapHelper by lazy {
        PagerSnapHelper()
    }
    private val bannerAdapter by lazy {
        BannerAdapter(mutableListOf())
    }
    private val scheduledExecutorService by lazy {
        Executors.newScheduledThreadPool(1)
    }
    private val bannerLayoutManager by lazy {
        LinearLayoutManager(context, HORIZONTAL, false)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.apply {
            layoutManager = bannerLayoutManager
            adapter = bannerAdapter
            bannerSnapHelper.attachToRecyclerView(this)
        }
    }

    fun setBannerData(newList: List<Image>) {
        bannerAdapter.refreshBannerData(newList)
        scheduledExecutorService.scheduleAtFixedRate({
            if (bannerLayoutManager.findFirstVisibleItemPosition() == bannerAdapter.itemCount -1){
                smoothScrollToPosition(0)
            }else{
                smoothScrollToPosition(bannerLayoutManager.findFirstVisibleItemPosition() + 1)
            }
        }, 2000, 4000, TimeUnit.MILLISECONDS)
    }

    fun showBanner() = run { this.visibility = View.VISIBLE }

    fun release() =
        run { if (scheduledExecutorService != null) scheduledExecutorService.shutdown() }
}