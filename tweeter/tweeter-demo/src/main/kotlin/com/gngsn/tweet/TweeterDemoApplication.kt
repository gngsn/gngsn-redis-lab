package com.gngsn.tweet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TweeterDemoApplication

fun main(args: Array<String>) {
    runApplication<TweeterDemoApplication>(*args) {
        println("Kotlin Application is RUNNING!")
    }
}
