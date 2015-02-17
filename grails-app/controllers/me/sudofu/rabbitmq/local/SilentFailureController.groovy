package me.sudofu.rabbitmq.local

import com.budjb.rabbitmq.publisher.RabbitMessagePublisher

import org.joda.time.DateTime

import groovy.json.JsonBuilder

class SilentFailureController {

    RabbitMessagePublisher rabbitMessagePublisher

    def index() {
        println "Sending."
        render(rabbitMessagePublisher.rpc {
            exchange = 'me.sudofu.rabbitmq.local'
            routingKey = 'silentFailure'
            body = 'foo'
        })
        println "Done."
    }

    def showError() {
        render(new JsonBuilder([time: new DateTime()]).toString())
    }
}
