package ru.alextroy.mvptest.view

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import ru.alextroy.mvptest.R
import ru.alextroy.mvptest.utils.EXTRA_MESSAGE

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val webView: WebView = findViewById(R.id.web_view)
        message?.let { webView.loadUrl(it) }
    }
}