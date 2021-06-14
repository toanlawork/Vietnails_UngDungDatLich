package com.fpoly.vietnails_18

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        GlobalScope.launch {
            print("${printA()} ${Thread.currentThread()}")
        }
    }
    Thread.sleep(1000)
}

fun printA(): Int {
    return 10
}
