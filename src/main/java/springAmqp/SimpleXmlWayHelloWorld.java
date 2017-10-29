package springAmqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 一个使用xml的Demo
 */
public class SimpleXmlWayHelloWorld {
    public static void main(String[] args) {
        ApplicationContext context =  new GenericXmlApplicationContext("xmlrabbit.xml");
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend("xmlQueue", "hello");
        String word = (String) template.receiveAndConvert("xmlQueue");
        System.out.println(word);
    }
}
