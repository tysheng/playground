package com.tysheng.playground

import com.google.gson.Gson
import org.junit.Test

/**
 * Created by tysheng
 * Date: 29/6/18 7:27 PM.
 * Email: tyshengsx@gmail.com
 */
class KTest2 {

    data class Foo(
            val i1: Int = 0,
            val i2: Int = 0,
            val s1: String = "s1",
            val s2: String = ""
    ) {
    }

    @Test
    fun gsonTest() {
        val fooJson = """
            {
            "s2":"s2"

            }
        """
        val foo = Gson().fromJson(fooJson, Foo2::class.java)
        println(foo)
    }

    @Test
    fun constructorTest() {
//        val constructor = Foo2::class.java.getDeclaredConstructor()
//        constructor.isAccessible = true
//        println(constructor.parameters)

        val constructors = Foo2::class.java.constructors
        constructors.forEach {constructor->
            constructor.isAccessible = true
            println(constructor.parameters.joinToString())
        }
    }
}