package foo

import android.app.Activity
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import java.util.*

/**
 * Created by tysheng
 * Date: 26/3/18 2:55 PM.
 * Email: tyshengsx@gmail.com
 */
interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter
    // ...
}

interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, we can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
    val activities = ArrayList<Activity>()
    a(activities)
    val arr1 = arrayOf(1)
    val arr2 = arrayOf(1f)
//    copy(arr1, arr2)
}

private fun <T> a(a: List<T>) {

}

fun copy(from: Array<out Any>, to: Array<Any>) {
    // ……
//    from[0] = 2f
//    to[0] = 1

}


fun main(args: Array<String>) {
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
}