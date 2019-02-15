package zone.share.demo

import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.common.IdentifierFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import zone.share.demo.common.CreateUserCommand

@RunWith(SpringRunner::class)
@SpringBootTest
class RabbitProducerApplicationTests {

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Test
    fun createUser() {
        commandGateway.sendAndWait<Any>(CreateUserCommand(seq = IdentifierFactory.getInstance().generateIdentifier(), username = "username"))
    }

}

