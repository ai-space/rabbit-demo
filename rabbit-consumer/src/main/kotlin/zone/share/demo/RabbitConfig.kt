package zone.share.demo

import org.axonframework.boot.AMQPProperties
import org.springframework.amqp.core.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import zone.share.demo.AMQPConfig.Companion.queue
import zone.share.demo.AMQPConfig.Companion.redirect

@Configuration
class RabbitConfig {

    @Autowired
    lateinit var properties: AMQPProperties

    @Bean
    fun exchange(): Exchange {
        val args = HashMap<String, String>()
        args["x-dead-letter-exchange"] = properties.exchange
        args["x-dead-letter-routing-key"] = "dead-letter"
        return ExchangeBuilder.topicExchange(properties.exchange).durable(true).withArguments(args.toMap()).build()
    }

    @Bean
    @Qualifier("dead-queue")
    fun deadQueue(): Queue {
        return QueueBuilder.durable(queue).build()
    }

    @Bean
    @Qualifier("redirect-queue")
    fun redirectQueue(): Queue {
        return QueueBuilder.durable(redirect).build()
    }

    @Bean
    @Qualifier("dead-binding")
    fun deadBinding(): Binding {
        return Binding(queue, Binding.DestinationType.QUEUE, properties.exchange, "#", null)
    }

    @Bean
    @Qualifier("redirect-binding")
    fun redirectBinding(): Binding {
        return Binding(redirect, Binding.DestinationType.QUEUE, properties.exchange, "#", null)
    }

}