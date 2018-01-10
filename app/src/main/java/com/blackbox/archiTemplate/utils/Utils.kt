package com.blackbox.archiTemplate.utils

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import io.reactivex.annotations.NonNull

/**
 * Created by umair on 10/01/2018.
 */
class Utils {

    companion object Factory {
        fun create(): Utils = Utils()
    }

    fun setTypeface(@NonNull type: Int, @NonNull context: Context): Typeface {

        val assets = context.assets

        var font = Typeface.createFromAsset(assets, "fonts/Roboto-Black.ttf")

        when (type) {
            0 -> font = Typeface.createFromAsset(assets, "fonts/Roboto-Black.ttf")
            1 -> font = Typeface.createFromAsset(assets, "fonts/Roboto-BlackItalic.ttf")
            2 -> font = Typeface.createFromAsset(assets, "fonts/Roboto-Bold.ttf")
            3 -> font = Typeface.createFromAsset(assets, "fonts/Roboto-BoldItalic.ttf")
            4 -> font = Typeface.createFromAsset(assets, "fonts/Roboto-Italic.ttf")
            5 -> font = Typeface.createFromAsset(assets, "fonts/Roboto-Light.ttf")
            6 -> font = Typeface.createFromAsset(assets, "fonts/Roboto-LightItalic.ttf")
            7 -> font = Typeface.createFromAsset(assets, "fonts/Roboto-Medium.ttf")
            8 -> font = Typeface.createFromAsset(assets, "fonts/Roboto-MediumItalic.ttf")
            9 -> font = Typeface.createFromAsset(assets, "fonts/Roboto-Regular.ttf")
            10 -> font = Typeface.createFromAsset(assets, "fonts/Roboto-Thin.ttf")
            11 -> font = Typeface.createFromAsset(assets, "fonts/Roboto-ThinItalic.ttf")
            12 -> font = Typeface.createFromAsset(assets, "fonts/RobotoCondensed-Bold.ttf")
            13 -> font = Typeface.createFromAsset(assets, "fonts/RobotoCondensed-BoldItalic.ttf")
            14 -> font = Typeface.createFromAsset(assets, "fonts/RobotoCondensed-Italic.ttf")
            15 -> font = Typeface.createFromAsset(assets, "fonts/RobotoCondensed-Light.ttf")
            16 -> font = Typeface.createFromAsset(assets, "fonts/RobotoCondensed-LightItalic.ttf")
            17 -> font = Typeface.createFromAsset(assets, "fonts/RobotoCondensed-Regular.ttf")
        }

        return font
    }


    fun isValidString(@NonNull s: String): String {
        var res = s
        if (!TextUtils.isEmpty(s)) {
            res = s
        }
        return res
    }

    fun isValidData(@NonNull s: String): Boolean {
        var isValid = false

        if (!TextUtils.isEmpty(s)) {
            isValid = true
        }
        return isValid
    }

    fun hideKeyboard(@NonNull context: Context?) {
        if (context != null) {
            val a = context as Activity?
            val imm = a!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(a.currentFocus!!.windowToken, 0)
        }
    }


    fun getInitials(@NonNull input: String): String {
        val sb = StringBuilder()
        for (s in input.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            sb.append(s[0])
        }
        return sb.toString().toUpperCase()
    }

}
