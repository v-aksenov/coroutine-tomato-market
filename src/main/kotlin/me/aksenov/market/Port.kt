package me.aksenov.market

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import org.springframework.stereotype.Component

@Component
class Port : Logger {

    suspend fun start(channel: Channel<Tomato>) {
        log.info("port started")
        goodsArriveCircle(channel)
    }

    private suspend fun goodsArriveCircle(channel: Channel<Tomato>) {
        var i = 0
        while (true) {
            log.info(">> TOMATO $i SENT")
            channel.send(Tomato(i++))
            delay(1000)
        }
    }
}
