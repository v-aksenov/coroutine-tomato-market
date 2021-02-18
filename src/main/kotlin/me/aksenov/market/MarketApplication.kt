package me.aksenov.market

import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MarketApplication

fun main(args: Array<String>) {
    val runApplication = runApplication<MarketApplication>(*args)
    runApplication.getBean<Orchestrator>(Orchestrator::javaClass).start()
}
