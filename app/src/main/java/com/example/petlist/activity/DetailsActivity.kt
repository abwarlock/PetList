package com.example.petlist.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.petlist.R
import com.example.petlist.models.Pets

class DetailsActivity : BaseActivity() {

    companion object {
        private const val ARG_PET_MODEL = "ARG_PET_MODEL"
        fun open(context: Context, petModel: Pets) {
            context.startActivity(Intent(context, DetailsActivity::class.java).apply {
                putExtra(ARG_PET_MODEL, petModel)
            })
        }
    }

    private var detailImage: ImageView? = null
    private var webView: WebView? = null

    private var mPetModel: Pets? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        detailImage = findViewById(R.id.detail_image)

        webView = findViewById(R.id.webView)
        webView?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }

        mPetModel = intent.getParcelableExtra(ARG_PET_MODEL) as Pets?
    }

    override fun onResume() {
        super.onResume()
        initUi()
    }

    private fun initUi() {
        title = mPetModel?.title

        detailImage?.let {
            Glide.with(this)
                .load(mPetModel?.imageUrl)
                .centerCrop()
                .fitCenter()
                .into(it)
        }

        mPetModel?.contentUrl?.let { webView?.loadUrl(it) }
    }
}