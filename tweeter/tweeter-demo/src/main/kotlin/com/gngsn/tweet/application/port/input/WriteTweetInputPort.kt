package com.gngsn.tweet.application.port.input

interface WriteTweetInputPort {
    fun write(userId: Long, message: String)
}