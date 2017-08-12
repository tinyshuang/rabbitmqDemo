package helloworld;

import com.rabbitmq.client.*;
import common.ConnectionTool;

import java.io.IOException;

/**
 * Created by asus on 2017/8/12.
 */
public class Rec {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        Connection connection = ConnectionTool.getConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");


        //回调函数
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };

        //String basicConsume(String queue,Consumer callback)
        //开启一个消费者,有消息时会触发callback通知
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
