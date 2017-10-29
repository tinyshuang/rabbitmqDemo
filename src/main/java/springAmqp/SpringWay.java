package springAmqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by asus on 2017/10/8.
 */
public class SpringWay {
    public static void main(String[] args) throws  Exception{
        AbstractApplicationContext ctx =
                new ClassPathXmlApplicationContext("context.xml");
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        template.convertAndSend("Hello, world!");
        Thread.sleep(1000);
        ctx.close();
    }
}
