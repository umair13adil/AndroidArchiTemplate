package com.blackbox.archiTemplate.ui.fragments

import android.content.Context
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection

/**
 * Created by umair on 10/01/2018.
 */
open class BaseFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}