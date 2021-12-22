package com.wseemann.crinkle

import android.webkit.JavascriptInterface

import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CrinkleInterface(private val resultTextView: TextView) {
    @JavascriptInterface
    fun sendResult(result: String) {
        CoroutineScope(Dispatchers.Main).launch {
            resultTextView.text = "You'll need $result of wrapping paper to wrap this gift!"
        }
    }
}
