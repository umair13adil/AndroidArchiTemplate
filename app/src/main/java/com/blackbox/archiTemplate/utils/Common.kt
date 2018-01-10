package com.blackbox.archiTemplate.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by umair on 08/01/2018.
 */
class Common {

    companion object {

        fun addFragment(fragmentManager: FragmentManager, fragment: Fragment, containerViewId: Int) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(containerViewId, fragment)
            transaction.commitAllowingStateLoss()
        }

        fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment, containerViewId: Int, addToBackStack: Boolean) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(containerViewId, fragment)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(fragment::class.java.name)
            }
            fragmentTransaction.commitAllowingStateLoss()
        }
    }
}