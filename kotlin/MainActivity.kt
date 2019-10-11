package com.example.helloworld0_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.R.attr.keySet


//import java.lang.Integer.parseInt


// http://try.kotl.in

class MainActivity : AppCompatActivity() {
    private final var TAG = "test_Kotlin";

    fun hasPrefix(x: Any) = when(x) {
        is String -> x.startsWith("prefix")
        else -> false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // ======================================
        var a = 1
        var b = 2

        // Traditional usage
        var max = a
        if (a < b) max = b

        // ======================================
        // With else
        if (a > b) {
            max = a
        } else {
            max = b
        }

        // ======================================
        // As expression
        max = if (a > b) a else b

        // ======================================
        max = if (a > b) {
            print("Choose a")
            a
        } else {
            print("Choose b")
            b
        }

        // ======================================
        var x = 1

        when (x) {
            1 -> Log.d(TAG,"x == 1")
            2 -> Log.d(TAG,"x == 2")
            else -> { // Note the block
                Log.d(TAG,"x is neither 1 nor 2")
            }
        }

        // ======================================
        x = 2
        when (x) {
            0, 1 -> Log.d(TAG,"x == 0 or x == 1")
            else -> Log.d(TAG,"otherwise")
        }

        when( hasPrefix("prefix-test") ) {
            true -> Log.d(TAG,"start with prefix")
            false -> Log.d(TAG,"other strings")
        }

        var args = listOf("FR", "test")

        val language = if (args.size == 0) "EN" else args[0]
        println(when (language) {
            "EN" -> Log.d("test_Kotlin","Hello!")
            "FR" -> Log.d("test_Kotlin","Salut!")
            "It" -> Log.d("test_Kotlin","Ciao!!")
            else -> Log.d("test_Kotlin","Sorry, I can't greet you in $language yet")
        })

        println(when (language) {
            "EN" -> "Hello!"
            "FR" -> "Salut!"
            "It" -> "Ciao!!"
            else -> "I can't greet you in $language yet"
        })

        Greeter("Sam Lee").greet()

        Log.d("test_Kotlin", "" + max("10".toInt(), "20".toInt()) + " : No.1" )
        Greeter("Sam Lee").greet()
        Log.d("test_Kotlin", max("10".toInt(), "20".toInt()).toString(10) + " : No.2" )
        Greeter("Sam Lee").greet()
        Log.d("test_Kotlin", "" + maxWithNullable(parseInt("10"), parseInt("20")) + " : No.3" )
        Greeter("Sam Lee").greet()

        val c = parseInt("10")
        val d = parseInt("20")

        if( c != null && d != null) {
            Log.d("test_Kotlin", "" + max(c, d) + " : No.4")
        } else {
            Log.d("test_Kotlin", "One of the arguments is null")
        }

        Log.d("test_Kotlin", "" + getStringLength("aaa"))
        Log.d("test_Kotlin", "" + getStringLength(1))

        var i = 0
        while (i < args.size)
            Log.d("test_Kotlin", args[i++])

        for (arg in args)
            Log.d("test_Kotlin",arg)

        println()
        for (i in args.indices)
            Log.d("test_Kotlin",args[i])


        x = 10

        //Check if a number lies within a range:
        val y = 15
        if (x in 1..y - 1)
            Log.d("test_Kotlin","OK")

        //Iterate over a range:
        for (a in 1..5)
            Log.d("test_Kotlin","${a} ")

        //Check if a number is out of range:
        Log.d("test_Kotlin","")
        val array = arrayListOf<String>()
        array.add("aaa")
        array.add("bbb")
        array.add("ccc")

        if (x !in 0..array.size - 1)
            Log.d("test_Kotlin","Out: array has only ${array.size} elements. x = ${x}")

        // can not use collection class here.
//        //Check if a collection contains an object:
//        if ("aaa" in array) // collection.contains(obj) is called
//            println("Yes: array contains aaa")
//
//        if("ddd" in array) // colection.contains(obj) is called
//            println("Yes: array contains ddd")
//        else
//            println("No: array doesn't contains ddd")

        cases("Hello")
        cases(1)
        cases(0L)
        cases(MyClass())
        cases("hello")


        // This example introduces a concept that we call destructuring declarations.
        val pair = Pair(1, "one")
        val (num, name) = pair
        Log.d("test_Kotlin","num = $num, name = $name")



        // Data class gets component functions, ...
        Log.d("test_Kotlin","getUser() - name, id")

        val user = getUser()
        Log.d("test_Kotlin","name = ${user.name}, id = ${user.id}" + " : #1")

        // or

        val (name2, id) = getUser()
        Log.d("test_Kotlin","name = $name2, id = $id" + " : #2")

        // or

        Log.d("test_Kotlin","name = ${getUser().component1()}, id = ${getUser().component2()}" + " : #3")

        // We can run it on the wetsite.
        // => https://try.kotlinlang.org/#/Examples/Destructuring%20declarations%20and%20Data%20classes/Data%20classes/Data%20classes.kt

        // ...

    }

    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------



    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------

    class Greeter(val name: String) {
        fun greet() {
            Log.d("test_Kotlin","Hello, ${name}")
        }
    }

    fun max(a: Int, b: Int) = if (a > b) a else b

    fun maxWithNullable(a: Int?, b: Int?): Int? {
        if( a ==  null || b == null) return null
        return if (a > b) a else b
    }

    fun parseInt(str: String): Int? {
        try {
            return str.toInt()
        } catch (e: NumberFormatException) {
            println("One of the arguments isn't Int")
        }
        return null
    }

    fun getStringLength(obj: Any): Int? {
        if (obj is String)
            return obj.length  // no cast to String is needed
        return null
    }

    fun cases(obj: Any) {
        when (obj) {
            1 -> Log.d("test_Kotlin","One")
            "Hello" -> Log.d("test_Kotlin","Greeting")
            is Long -> Log.d("test_Kotlin","Long")
            !is String -> Log.d("test_Kotlin","Not a string")
            else -> Log.d("test_Kotlin","Unknown")
        }
    }

    class MyClass() {
    }

    class Pair<K, V>(val first: K, val second: V) {
        operator fun component1(): K {
            return first
        }

        operator fun component2(): V {
            return second
        }
    }

    data class User(val name: String, val id: Int)

    fun getUser(): User {
        return User("Alex", 1)
    }


}
