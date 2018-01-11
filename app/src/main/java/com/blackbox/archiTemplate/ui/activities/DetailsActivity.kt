package com.blackbox.archiTemplate.ui.activities

/**
 * Created by umair on 10/01/2018.
 */

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.blackbox.archiTemplate.R
import com.blackbox.archiTemplate.ui.items.PostItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailsActivity : BaseActivity() {

    val TAG: String = DetailsActivity::class.java.simpleName

    companion object {

        var EXTRA_ITEM = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Setup toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        //Get details of PostItem
        if (intent.extras.get(EXTRA_ITEM) != null) {
            val postItem = intent.extras.get(EXTRA_ITEM) as PostItem
            setContent(postItem)
        }
    }

    private fun setContent(postItem: PostItem) {

        //Set content in Collapsing Toolbar
        toolbar_layout.title = postItem.title
        toolbar_layout.setCollapsedTitleTextAppearance(R.style.collapsedAppBar)
        toolbar_layout.setExpandedTitleTextAppearance(R.style.expandedAppBar)
        toolbar_layout.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
        toolbar_layout.setStatusBarScrimColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))

        Glide.with(this).load(postItem.image).into(img_header)

        //Set content in details section
        txt_title.text = postItem.title
        txt_description.text = postItem.details
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}