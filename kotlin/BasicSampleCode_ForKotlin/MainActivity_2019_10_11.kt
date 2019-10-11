package com.example.helloworld0_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.R.attr.keySet
import kotlin.properties.Delegates
import kotlin.properties.Delegates.observable
import kotlin.reflect.KProperty


//import java.lang.Integer.parseInt


// http://try.kotl.in

class MainActivity : AppCompatActivity() {
    private final var TAG = "test_Kotlin";

    fun hasPrefix(x: Any) = when (x) {
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
            1 -> Log.d(TAG, "x == 1")
            2 -> Log.d(TAG, "x == 2")
            else -> { // Note the block
                Log.d(TAG, "x is neither 1 nor 2")
            }
        }

        // ======================================
        x = 2
        when (x) {
            0, 1 -> Log.d(TAG, "x == 0 or x == 1")
            else -> Log.d(TAG, "otherwise")
        }

        when (hasPrefix("prefix-test")) {
            true -> Log.d(TAG, "start with prefix")
            false -> Log.d(TAG, "other strings")
        }

        var args = mutableListOf("FR", "test")

        val language = if (args.size == 0) "EN" else args[0]
        println(when (language) {
            "EN" -> Log.d("test_Kotlin", "Hello!")
            "FR" -> Log.d("test_Kotlin", "Salut!")
            "It" -> Log.d("test_Kotlin", "Ciao!!")
            else -> Log.d("test_Kotlin", "Sorry, I can't greet you in $language yet")
        })

        println(when (language) {
            "EN" -> "Hello!"
            "FR" -> "Salut!"
            "It" -> "Ciao!!"
            else -> "I can't greet you in $language yet"
        })

        Greeter("Sam Lee").greet()

        Log.d(TAG, "" + max("10".toInt(), "20".toInt()) + " : No.1")
        Greeter("Sam Lee").greet()
        Log.d(TAG, max("10".toInt(), "20".toInt()).toString(10) + " : No.2")
        Greeter("Sam Lee").greet()
        Log.d(TAG, "" + maxWithNullable(parseInt("10"), parseInt("20")) + " : No.3")
        Greeter("Sam Lee").greet()

        val c = parseInt("10")
        val d = parseInt("20")

        if (c != null && d != null) {
            Log.d(TAG, "" + max(c, d) + " : No.4")
        } else {
            Log.d(TAG, "One of the arguments is null")
        }

        Log.d(TAG, "" + getStringLength("aaa"))
        Log.d(TAG, "" + getStringLength(1))

        var i = 0
        while (i < args.size)
            Log.d(TAG, args[i++])

        for (arg in args)
            Log.d(TAG, arg)

        println()
        for (i in args.indices)
            Log.d(TAG, args[i])


        x = 10

        //Check if a number lies within a range:
        val y = 15
        if (x in 1..y - 1)
            Log.d(TAG, "OK")

        //Iterate over a range:
        for (a in 1..5)
            Log.d(TAG, "${a} ")

        //Check if a number is out of range:
        Log.d(TAG, "")
        val array = arrayListOf<String>()
        array.add("aaa")
        array.add("bbb")
        array.add("ccc")

        if (x !in 0..array.size - 1)
            Log.d(TAG, "Out: array has only ${array.size} elements. x = ${x}")

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
        Log.d(TAG, "num = $num, name = $name")


        // Data class gets component functions, ...
        Log.d(TAG, "getUser() - name, id")

        val user = getUser()
        Log.d(TAG, "name = ${user.name}, id = ${user.id}" + " : #1")

        // or

        val (name2, id) = getUser()
        Log.d(TAG, "name = $name2, id = $id" + " : #2")

        // or

        Log.d(TAG, "name = ${getUser().component1()}, id = ${getUser().component2()}" + " : #3")

        // We can run it on the wetsite.
        // => https://try.kotlinlang.org/#/Examples/Destructuring%20declarations%20and%20Data%20classes/Data%20classes/Data%20classes.kt

        // ...

        //-------------------------------------------------------------------------------
        val map = hashMapOf<String, Int>()
        map.put("one", 1)
        map.put("two", 2)

        for ((key, value) in map) {
            Log.d(TAG, "key = $key, value = $value")
        }

        //-------------------------------------------------------------------------------
        val user2 = User("Alex", 1)
        Log.d(TAG, "" + user2) // toString()

        val secondUser = User("Alex", 1)
        val thirdUser = User("Max", 2)

        Log.d(TAG, "user == secondUser: ${user2 == secondUser}")
        Log.d(TAG, "user == thirdUser: ${user2 == thirdUser}")

        // copy() function
        Log.d(TAG, "" + user2.copy())
        Log.d(TAG, "" + user2.copy("Max"))
        Log.d(TAG, "" + user2.copy(id = 2))
        Log.d(TAG, "" + user2.copy("Max", 2))

        //-------------------------------------------------------------------------------
        val e = Example()
        Log.d(TAG, e.p)
        e.p = "NEW"

        //-------------------------------------------------------------------------------
        val sample = LazySample()
        Log.d(TAG, "lazy = $(sample.lazy}")
        Log.d(TAG, "lazy = $(sample.lazy}")

        //-------------------------------------------------------------------------------
        val userForDelegate = UserForDelegate()
        userForDelegate.name = "Carl"

        //-------------------------------------------------------------------------------
        val userForNotNull = userForNotNull()
        userForNotNull.init("Carl")
        Log.d(TAG, userForNotNull.name)


        //-------------------------------------------------------------------------------
        val userForMap = UserForMap(mapOf(
                "name" to "Johh Doe",
                "age" to 25
        ))

        Log.d(TAG, "name = ${userForMap.name}, age = ${userForMap.age}")

        //-------------------------------------------------------------------------------
        val numbers = listOf(1, 2, 3)
        Log.d(TAG, "" + numbers.filter(::isOdd))

        //-------------------------------------------------------------------------------
        val oddLength = compose(::isOdd, ::length)
        val strings = listOf("a", "ab", "abc")
        Log.d(TAG, "" + strings.filter(oddLength))

        //-------------------------------------------------------------------------------

        args = mutableListOf("FR", "test")
        args[0] = "10"
        //args[0] = "abc"
        //args[0] = null.toString()
        //args = mutableListOf()
        if(args.isEmpty) {
            printBottles(99)
        } else {
            try {
                printBottles(args[0].toInt())
            } catch (e: NumberFormatException) {
                Log.d(TAG,"You have passed '${args[0]}' as a number of bottles, " +
                        "but it is not a valid integer number")
            }
        }

        //-------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------


    }

    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------

    class Greeter(val name: String) {
        fun greet() {
            Log.d("test_Kotlin", "Hello, ${name}")
        }
    }
    //-------------------------------------------------------------------------------------

    fun max(a: Int, b: Int) = if (a > b) a else b

    fun maxWithNullable(a: Int?, b: Int?): Int? {
        if (a == null || b == null) return null
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

    //-------------------------------------------------------------------------------------
    fun getStringLength(obj: Any): Int? {
        if (obj is String)
            return obj.length  // no cast to String is needed
        return null
    }

    fun cases(obj: Any) {
        when (obj) {
            1 -> Log.d(TAG, "One")
            "Hello" -> Log.d(TAG, "Greeting")
            is Long -> Log.d(TAG, "Long")
            !is String -> Log.d(TAG, "Not a string")
            else -> Log.d(TAG, "Unknown")
        }
    }

    class MyClass() {
    }

    //-------------------------------------------------------------------------------------
    class Pair<K, V>(val first: K, val second: V) {
        operator fun component1(): K {
            return first
        }

        operator fun component2(): V {
            return second
        }
    }

    //-------------------------------------------------------------------------------------
    data class User(val name: String, val id: Int)

    fun getUser(): User {
        return User("Alex", 1)
    }

    //-------------------------------------------------------------------------------------
    class Example {
        var p: String by Delegate()
        override fun toString() = "Example Class"
    }

    class Delegate() {
        operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
            return "$thisRef, thank you for delegating '${prop.name}' to me!"
        }

        operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
            Log.d("test_Kotlin", "$value has been assigned to ${prop.name} in $thisRef")
        }
    }

    //-------------------------------------------------------------------------------------
    class LazySample {
        val lazy: String by lazy {
            Log.d("test_Kotlin", "computed!")
            "my lazy"
        }
    }

    //-------------------------------------------------------------------------------------
    class UserForDelegate {
        var name: String by observable("no name") { d, old, new ->
            Log.d("test_Kotlin", "$old - $new")
        }
    }

    //-------------------------------------------------------------------------------------
    class userForNotNull {
        var name: String by Delegates.notNull()

        fun init(name: String) {
            this.name = name
        }
    }

    //-------------------------------------------------------------------------------------
    class UserForMap(val map: Map<String, Any?>) {
        val name: String by map
        val age: Int by map
    }

    //-------------------------------------------------------------------------------------
    //fun isOdd(x: Int) = x % 2 != 0

    //-------------------------------------------------------------------------------------
    fun isOdd(x: Int) = x % 2 != 0
    fun length(s: String) = s.length

    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
        return { x -> f(g(x)) }
    }

    //-------------------------------------------------------------------------------------
    fun printBottles(bottleCount: Int) {
        if (bottleCount <= 0) {
            Log.d(TAG,"No bottles - no song")
            return
        }

        Log.d(TAG,"The \"${bottlesOfBeer(bottleCount)}\" song\n")

        var bottles = bottleCount
        while (bottles > 0) {
            val bottlesOfBeer = bottlesOfBeer(bottles)
            Log.d(TAG,"$bottlesOfBeer on the wall, $bottlesOfBeer. \nTake one down, pass it arround,")
            bottles--
            Log.d(TAG,"${bottlesOfBeer(bottles)} on the wall.\n")
        }
        Log.d(TAG,"No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, ${bottlesOfBeer(bottleCount)} on the wall.")
    }

    fun bottlesOfBeer(count: Int): String =
            when (count) {
                0 -> "no more bottles"
                1 -> "1 bottle"
                else -> "$count bottles"
            } + " of beer"

    //val <T> Array<T>.isEmpty: Boolean get() = size ==0

    private val <T> MutableList<T>.isEmpty: Boolean get() = size == 0
    //private val <E> MutableList<E>.isEmpty: Boolean get() {}


    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------


}

