package zone.share.demo

import com.rabbitmq.client.Channel
import org.axonframework.amqp.eventhandling.AMQPMessageConverter
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.RuntimeException

@Configuration
class AMQPConfig {

    @Bean
    fun axonQueueMessageSource(messageConverter: AMQPMessageConverter): SpringAMQPMessageSource {
        return object : SpringAMQPMessageSource(messageConverter) {
            @RabbitListener(queues = ["zone.share.demo.consumer"])
            override fun onMessage(message: Message, channel: Channel) {
                try {
                    super.onMessage(message, channel)
                } catch (ex: RuntimeException) {
                    println("=================")
                    ex.printStackTrace()
                }
            }
        }
    }

}
