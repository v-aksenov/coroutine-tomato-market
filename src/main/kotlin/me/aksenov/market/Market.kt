package me.aksenov.market

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.stereotype.Component

@Component
class Market : Logger {

    private val storage = arrayListOf<Tomato>()

    suspend fun start(channel: Channel<Tomato>) {
        log.info("market opened")
        GlobalScope.launch {
            sellCircle()
        }
        GlobalScope.launch {
            fillStorageCircle(channel)
        }
    }

    private suspend fun sellCircle() {
        var inc = 0
        while (true) {
            storage.removeFirstOrNull()?.let {
                log.info("TOMATO ${it.id} SOLD >>. In storage: ${storage.size}")
            }
            delay(10000)
        }
    }

    private suspend fun fillStorageCircle(channel: Channel<Tomato>) {
        while (true) {
            channel.receive().let {
                storage.add(it)
                log.info("TOMATO ${it.id} >> ARRIVED. In storage: ${storage.size}")
                delay(3000)
            }
        }
    }
}
