package me.aksenov.market

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class Orchestrator(
    private val port: Port,
    private val market: Market
) : Logger {

    fun start() = runBlocking {
        val channel = Channel<Tomato>()
        log.info("orchestration started")
        launch { market.start(channel) }
        launch { port.start(channel) }
    }
}
