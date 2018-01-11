package com.blackbox.archiTemplate.utils

import android.graphics.PointF
import android.view.View

/**
 * Created by umair on 10/01/2018.
 */
/**
 * Transition informations structure (view, touch point, ...).
 */
class TransitionInformation {

    /**
     * Covering view.
     */
    var coveringView: View? = null

    /**
     * Touch point.
     */
    var touchPoint: PointF? = null

    /**
     * Remember transition.

     * @param coveringView Covering view.
     * *
     * @param touchPoint   Touch point.
     */
    fun rememberTransition(coveringView: View, touchPoint: PointF) {
        this.coveringView = coveringView
        this.touchPoint = touchPoint
    }

    /**
     * Clear informations.
     */
    fun clear() {
        coveringView = null
        touchPoint = null
    }
}
