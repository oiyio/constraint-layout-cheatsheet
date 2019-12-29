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

package com.hossainkhan.android.demo.di

import com.hossainkhan.android.demo.ui.functionaldemo.MovieDetailsActivity
import com.hossainkhan.android.demo.ui.functionaldemo.TedTalkPlaybackActivity
import com.hossainkhan.android.demo.ui.layoutpreview.ChainStyleActivity
import com.hossainkhan.android.demo.ui.layoutpreview.DimensionMinMaxActivity
import com.hossainkhan.android.demo.ui.layoutpreview.GuidelineBarrierActivity
import com.hossainkhan.android.demo.ui.layoutpreview.GuidelineGroupActivity
import com.hossainkhan.android.demo.ui.layoutpreview.BaseActivity
import com.hossainkhan.android.demo.ui.layoutpreview.VisibilityGoneActivity
import com.hossainkhan.android.demo.ui.resource.LearningResourceActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever
 * module ActivityBindingModule is on, in our case that will be [DemoApplicationComponent].
 *
 * The beautiful part about this setup is that you never need to tell [DemoApplicationComponent]
 * that it is going to have all these subcomponents nor do you need to tell these subcomponents
 * that [DemoApplicationComponent] exists.
 *
 * We are also telling Dagger.Android that this generated SubComponent needs to include
 * the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create subcomponents for us.
 */
@Module
abstract class ActivityBindingModule {
    /*
     * https://google.github.io/dagger/android.html
     * Pro-tip: If your subcomponent and its builder have no other methods or supertypes than
     * the ones mentioned in step #2, you can use @ContributesAndroidInjector to generate them for you.
     *
     * Instead of steps 2 and 3, add an abstract module method that returns your activity,
     * annotate it with @ContributesAndroidInjector, and specify the modules you want to install
     * into the subcomponent. If the subcomponent needs scopes, apply the scope annotations to
     * the method as well.
     */

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun layoutPreviewBaseActivity(): BaseActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun layoutVisibilityActivity(): VisibilityGoneActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun layoutChainActivity(): ChainStyleActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun layoutGuidelineBarrierActivity(): GuidelineBarrierActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun layoutGuidelineGroupActivity(): GuidelineGroupActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun layoutDimensionMinMaxActivity(): DimensionMinMaxActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun layoutMovieDetailsPreviewActivity(): MovieDetailsActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun layoutTedTalkPlaybackPreviewActivity(): TedTalkPlaybackActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun learningResourceActivity(): LearningResourceActivity
}