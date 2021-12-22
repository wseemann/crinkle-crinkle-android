package com.wseemann.crinkle

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wseemann.crinkle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.webview.settings.javaScriptEnabled = true
        binding.webview.addJavascriptInterface(CrinkleInterface(binding.resultTextView), "Crinkle")

        binding.crinkleButton.setOnClickListener {
            calculateSize()
        }
    }

    private fun calculateSize() {
        val length = binding.lengthEdittext.text
        val width = binding.widthEdittext.text
        val height = binding.heightEdittext.text

        val inputStream = resources.assets.open("crinkle.html")
        val html = String(inputStream.readBytes()).replace("<request>", "wrapThatGift($length, $width, $height);")

        binding.webview.loadData(html, "text/html; charset=UTF-8", null)
    }
}
