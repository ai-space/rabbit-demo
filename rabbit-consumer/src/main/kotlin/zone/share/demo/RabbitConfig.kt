package zone.share.demo

import org.axonframework.boot.AMQPProperties
import org.springframework.amqp.core.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

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
        return QueueBuilder.durable("zone.share.demo.consumer").build()
    }

    @Bean
    @Qualifier("redirect-queue")
    fun redirectQueue(): Queue {
        return QueueBuilder.durable("zone.share.demo.consumer.redirect").build()
    }

    @Bean
    @Qualifier("dead-binding")
    fun deadBinding(): Binding {
        return Binding("zone.share.demo.consumer", Binding.DestinationType.QUEUE, properties.exchange, "#", null)
    }

    @Bean
    @Qualifier("redirect-binding")
    fun redirectBinding(): Binding {
        return Binding("zone.share.demo.consumer.redirect", Binding.DestinationType.QUEUE, properties.exchange, "#", null)
    }

}