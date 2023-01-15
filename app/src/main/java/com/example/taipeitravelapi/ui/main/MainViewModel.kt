package com.example.taipeitravelapi.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taipeitravelapi.LangDef
import com.example.taipeitravelapi.model.ItemAttraction
import com.example.taipeitravelapi.repo.MainRepo
import com.example.taipeitravelapi.utils.ApiResult
import com.example.taipeitravelapi.utils.JsonUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MainRepo,
    private val jsonUtils: JsonUtils,
) : ViewModel() {

    val TAG = this::class.simpleName
    var attractionList: MutableLiveData<List<ItemAttraction>> = MutableLiveData(emptyList())

    init {
        getAttractionsList()
    }

    private fun getAttractionsList(lang: String = getLangTranslate(LangDef.zh_tw.name)) {
        val map = mutableMapOf<String, String>()
        map["page"] = "1"
        viewModelScope.launch {
            when (val result = repo.getAttractionsList(lang, map)) {
                is ApiResult.OnSuccess -> {
                    attractionList.value =
                        jsonUtils.gsonFromJson(jsonUtils.gson2Json(result.data.data))
                }
                is ApiResult.OnFail -> {

                }
                is ApiResult.OnException -> {

                }
            }
        }
    }

    fun replaceLanguage(newLang: String) = getAttractionsList(getLangTranslate(newLang))

    private fun getLangTranslate(lang: String): String {
        return when (lang) {
            LangDef.zh_tw.name -> "zh-tw"
            LangDef.zh_cn.name -> "zh-cn"
            else -> lang
        }
    }
}