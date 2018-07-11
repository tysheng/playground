package com.tysheng.playground

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * Created by tysheng
 * Date: 25/4/18 6:00 PM.
 * Email: tyshengsx@gmail.com
 */
class MemeberListActivity : Activity() {
    lateinit var frameLayout: FrameLayout

    val TEST_AVATAR = "https://beebom-redkapmedia.netdna-ssl.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        frameLayout = FrameLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
        setContentView(frameLayout)
        val membersLinearView = MembersLinearView(this)
        membersLinearView.apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        frameLayout.addView(membersLinearView)
        val list = Array(5){
            TEST_AVATAR
        }.toMutableList()
        membersLinearView.iconList = list
    }
}