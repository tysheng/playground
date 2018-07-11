package com.tysheng.playground

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import kotlin.properties.Delegates

/**
 * Created by tysheng
 * Date: 23/3/18 11:02 AM.
 * Email: tyshengsx@gmail.com
 */
class KtTest {
    fun main(args: Array<String>) {
//        launch {
//            // launch new coroutine in background and continue
//            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
//            println("World!") // print after delay
//        }
//        println("Hello,") // main thread continues while coroutine is delayed
//        Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
//
//        async {
//            arrayListOf(1,2)
//                    .forEach {
//                        doSth()
//                    }
//           doSth()
//        }


    }

    @Test
    fun doSth() {
        runBlocking {

            val jobs = List(10_000) {
                launch {
                    delay(1000)
                    print(".")
                }
            }
            jobs.forEach {
                it.join()
            }
        }
        Delegates.observable(1) { t, i1, i2 ->

        }
    }

    @Test
    fun removeButterKnife() {
        val str = """
                @BindView(R.id.root)
    internal var mRootView: View? = null

    @BindView(R.id.article)
    internal var mArticleView: RecyclerView? = null

    @BindView(R.id.image_btn_comment_send)
    internal var mSendButton: View? = null

    @BindView(R.id.edit_comment)
    internal var mEditCommentBar: TextView? = null

    @BindView(R.id.toolbar_user_avatar)
    internal var toolbarUserAvatar: ImageView? = null

    @BindView(R.id.toolbar_user_name)
    internal var toolbarUserName: TextView? = null

    @BindView(R.id.toolbar_follow)
    internal var toolbarFollow: FollowButton? = null

    @BindView(R.id.attached_bar)
    internal var mAttachedBar: View? = null

    @BindView(R.id.comment_input)
    internal var mCommentBar: View? = null


    @BindView(R.id.action_like)
    internal var actionLike: ImageView? = null

    @BindView(R.id.toolbar_user_info_layout)
    internal var toolbarUserInfoLayout: View? = null
            """

        var id = ""
        val pattern = "(R\\.id\\.\\S*)\\)".toPattern()
        val lines = str.reader().readLines()
        var count = 0
        while (count < lines.size) {
            val line = lines[count]

            val raw = line.trim()
            when {
                raw.startsWith("@") -> {
                    val matcher = pattern.matcher(raw)
                    if (matcher.find()) {
                        id = matcher.group(1)
//                        println("id :$id")
                    }
                }
                raw.isNotBlank() -> {
                    val sub = raw.substringBefore("?") + "= findViewById(" + id + ")"
                    println(sub)
                }
            }
            count++
        }
    }

    fun foo(p1: Int, p2: String? = null) {

    }

    fun foo(p1: Int, p2: Int = 0) {

    }

//    fun List<String>.filterValid(): List<String>{
//       return this
//    }
//    fun List<Int>.filterValid(): List<Int>{
//        return this
//    }
    @Test
    fun upload() {
//        foo(1)

//arrayListOf(1).filterValid()

  println(  "${1.inv()} + ${1.inc()}")
    }

}