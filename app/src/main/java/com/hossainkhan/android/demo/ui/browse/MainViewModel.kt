/*
 * Copyright (c) 2018 Hossain Khan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hossainkhan.android.demo.ui.browse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.data.AppDataStore
import com.hossainkhan.android.demo.data.LayoutInformation
import com.hossainkhan.android.demo.ui.common.LiveEvent
import com.hossainkhan.android.demo.ui.functionaldemo.MovieDetailsActivity
import com.hossainkhan.android.demo.ui.functionaldemo.TedTalkPlaybackActivity
import com.hossainkhan.android.demo.ui.layoutpreview.ChainStyleActivity
import com.hossainkhan.android.demo.ui.layoutpreview.DimensionMinMaxActivity
import com.hossainkhan.android.demo.ui.layoutpreview.GuidelineBarrierActivity
import com.hossainkhan.android.demo.ui.layoutpreview.GuidelineGroupActivity
import com.hossainkhan.android.demo.ui.layoutpreview.VisibilityGoneActivity
import com.hossainkhan.android.demo.ui.resource.LearningResourceActivity
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
        appDataStore: AppDataStore
) : ViewModel() {

    private val _browseResult = LiveEvent<MainResult<Any>>()
    val mainResult: LiveData<MainResult<Any>> = _browseResult
    private val layoutInfoListLiveData = MutableLiveData<List<LayoutInformation>>()

    val layoutInfos: LiveData<List<LayoutInformation>>
        get() = layoutInfoListLiveData

    init {
        Timber.d("Is first time user: ${appDataStore.isFirstTime()}")
        appDataStore.updateFirstTimeUser(false)


        layoutInfoListLiveData.value = appDataStore.layoutStore.supportedLayoutInfos
    }


    fun onLayoutItemSelected(layoutResId: Int) {
        Timber.i("Selected layout id: %s", layoutResId)

        /*
         * Where applicable, loads specific activity with interactive feature for user to try out feature.
         */
        when (layoutResId) {
            R.layout.activity_visibility_gone -> {
                _browseResult.value = MainResult(VisibilityGoneActivity::class.java, layoutResId)
            }
            R.layout.activity_chain_style -> {
                _browseResult.value = MainResult(ChainStyleActivity::class.java, layoutResId)
            }
            R.layout.activity_guideline_barrier -> {
                _browseResult.value = MainResult(GuidelineBarrierActivity::class.java, layoutResId)
            }
            R.layout.activity_guideline_group -> {
                _browseResult.value = MainResult(GuidelineGroupActivity::class.java, layoutResId)
            }
            R.layout.activity_dimension_min_max -> {
                _browseResult.value = MainResult(DimensionMinMaxActivity::class.java, layoutResId)
            }
            R.layout.activity_movie_details -> {
                _browseResult.value = MainResult(MovieDetailsActivity::class.java, layoutResId)
            }
            R.layout.activity_ted_talk_playback -> {
                _browseResult.value = MainResult(TedTalkPlaybackActivity::class.java, layoutResId)
            }
            R.layout.activity_learning_resource -> {
                _browseResult.value = MainResult(LearningResourceActivity::class.java)
            }
            else -> {
                // By default it loads the preview activity with the layout requested.
                _browseResult.value = MainResult(layoutResId = layoutResId)
            }
        }

    }

    fun onExternalResourceSelected() {
        _browseResult.value = MainResult(LearningResourceActivity::class.java)
    }
}