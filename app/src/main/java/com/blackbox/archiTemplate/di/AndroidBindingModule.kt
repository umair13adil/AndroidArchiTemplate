package com.blackbox.archiTemplate.di

import com.blackbox.archiTemplate.ui.activities.MainActivity
import com.blackbox.archiTemplate.ui.fragments.PostListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by umair on 09/01/2018.
 */
@Module
internal abstract class AndroidBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun postListFragment(): PostListFragment

}