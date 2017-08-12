package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import common.ConnectionTool;

/**
 * Created by asus on 2017/8/12.
 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {

        Connection connection = ConnectionTool.getConnection();
        Channel channel = connection.createChannel();

        //queueDeclare(String queue,boolean durable,boolean exclusive,boolean autoDelete,Map<String,Object> arguments)
        //声明mq
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";


        //void basicPublish(String exchange,String routingKey,AMQP.BasicProperties props,byte[] body)
        //发布消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
