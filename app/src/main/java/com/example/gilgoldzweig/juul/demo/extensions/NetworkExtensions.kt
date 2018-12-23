package com.example.gilgoldzweig.juul.demo.extensions

import com.example.gilgoldzweig.movies.extensions.Timber
import io.reactivex.Completable
import io.reactivex.Single
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

fun hasInternetConnection() = Completable.create {
    emitter ->
    try {
        // Connect to CloudFlair DNS to check for connection maximum time 1 second
        val timeoutMs = 1000
        val socket = Socket()
        val socketAddress = InetSocketAddress("1.1.1.1", 53)

        socket.connect(socketAddress, timeoutMs)
        socket.close()
        emitter.onComplete()
    } catch (e: IOException) {
        Timber.e(e)
        emitter.onError(e)
    }
}