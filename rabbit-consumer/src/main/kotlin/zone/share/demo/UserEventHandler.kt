package zone.share.demo

import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component
import zone.share.demo.common.UserCreatedEvent
import java.lang.RuntimeException

/**
 * UserEventHandler
 * @author Iamee
 * @create 2019-02-15 21:59
 */
@Component
@ProcessingGroup("user")
class UserEventHandler {

    var index = 0

    @EventHandler
    fun on(event: UserCreatedEvent) {
        println("====input${++index}")
        throw RuntimeException("错误")
    }

}