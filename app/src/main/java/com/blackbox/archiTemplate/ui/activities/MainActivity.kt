package com.blackbox.archiTemplate.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.blackbox.archiTemplate.R
import com.blackbox.archiTemplate.ui.fragments.PostListFragment
import com.blackbox.archiTemplate.utils.Common
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by umair on 10/01/2018.
 */

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    var TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {

        //Pass this activity to Android Injector
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Add fragment to container
        Common.replaceFragment(
                supportFragmentManager,
                PostListFragment.newInstance(),
                R.id.main_content, false)

    }
}
