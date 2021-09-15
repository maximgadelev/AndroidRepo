package com.example.myapplication

import java.util.function.IntToLongFunction

open class Worker(
    var name: String,
    var surname :String,
    var workerId:Int,
    var isOnWork:Boolean,
    var salary:Int

) {
    override fun toString(): String {
        return "Worker(name='$name', surname='$surname', workerId=$workerId, isOnWork=$isOnWork, salary=$salary)"
    }
}
