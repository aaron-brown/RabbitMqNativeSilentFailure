package me.sudofu.rabbitmq.local

import com.budjb.rabbitmq.consumer.MessageContext
import com.budjb.rabbitmq.publisher.RabbitMessagePublisher

import org.joda.time.DateTime

class SilentFailureConsumer {
    RabbitMessagePublisher rabbitMessagePublisher

    /**
     * Consumer configuration.
     */
    static rabbitConfig = [
        consumers: 1,
        queue: 'test.silentFailure',
    ]

    /**
     * Handle an incoming RabbitMQ message.
     *
     * @param body    The converted body of the incoming message.
     * @param context Properties of the incoming message.
     * @return
     */
    def handleMessage(MessageContext context) {
        println "Returning DateTime."
        println "${context.properties.replyTo}"
        rabbitMessagePublisher.send {
            routingKey = context.properties.replyTo
            body = [
                time: new DateTime()
            ]
        }
        println "DateTime returned."
    }
}
