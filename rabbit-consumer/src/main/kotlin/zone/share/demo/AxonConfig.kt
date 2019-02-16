package zone.share.demo

import org.axonframework.config.EventHandlingConfiguration
import org.axonframework.eventhandling.ListenerInvocationErrorHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonConfig {

    @Bean
    fun eventHandlingConfiguration(): EventHandlingConfiguration {
        val configuration = EventHandlingConfiguration()
        configuration.configureListenerInvocationErrorHandler("user") {
            ListenerInvocationErrorHandler { exception, _, _ -> throw exception }
        }
        return configuration
    }

}