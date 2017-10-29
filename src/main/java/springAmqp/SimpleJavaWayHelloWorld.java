package springAmqp;

import common.Const;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 一个最简单的入门demo
 */
public class SimpleJavaWayHelloWorld {
    public static void main(String[] args) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(Const.host);
        connectionFactory.setUsername(Const.userName);
        connectionFactory.setPassword(Const.password);
        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
        admin.declareQueue(new Queue("simpleQueue"));
        AmqpTemplate template = new RabbitTemplate(connectionFactory);
        template.convertAndSend("simpleQueue", "helloworld");
        String foo = (String) template.receiveAndConvert("simpleQueue");
        System.out.println(foo);
    }
}
