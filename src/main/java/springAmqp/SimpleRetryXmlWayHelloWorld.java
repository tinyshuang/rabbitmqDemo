package springAmqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 一个使用xml的Demo
 */
public class SimpleRetryXmlWayHelloWorld {
    public static void main(String[] args) {
        ApplicationContext context =  new GenericXmlApplicationContext("retryXmlrabbit.xml");
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend("xmlQueue", "hello");
        String word = (String) template.receiveAndConvert("xmlQueue");
        template.send("xmlQueue",new Message("hello".getBytes(),new MessageProperties()));
        System.out.println(word);
    }
}
