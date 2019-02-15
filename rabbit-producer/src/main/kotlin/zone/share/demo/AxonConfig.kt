package zone.share.demo

import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * AxonConfig
 * @author Iamee
 * @create 2019-02-15 22:02
 */
@Configuration
class AxonConfig {

    @Bean
    fun eventStorageEngine(): EventStorageEngine {
        return InMemoryEventStorageEngine()
    }

}