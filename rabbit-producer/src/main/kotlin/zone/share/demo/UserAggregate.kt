package zone.share.demo

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle
import org.axonframework.eventhandling.EventHandler
import org.axonframework.spring.stereotype.Aggregate
import zone.share.demo.common.CreateUserCommand
import zone.share.demo.common.UserCreatedEvent

/**
 * UserAggregate
 * @author Iamee
 * @create 2019-02-15 21:17
 */
@Aggregate
class UserAggregate {

    @AggregateIdentifier
    lateinit var username: String

    constructor()

    @CommandHandler
    constructor(command: CreateUserCommand) {
        AggregateLifecycle.apply(UserCreatedEvent(seq = command.seq, username = command.username, time = System.currentTimeMillis()))
    }

    @EventHandler
    fun on(event: UserCreatedEvent) {
        this.username = event.username
    }

}