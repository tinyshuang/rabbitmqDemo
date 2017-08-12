package subscribe;


import com.rabbitmq.client.*;
import common.ConnectionTool;

import java.io.IOException;

public class ReceiveLogs {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        Connection connection = ConnectionTool.getConnection();
        Channel channel = connection.createChannel();

        //声明一个exchage类型为FANOUT的交换区
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        //获取一个临时的,自动删除的队列名
        String queueName = channel.queueDeclare().getQueue();

        //queueBind(String queue,String exchange,String routingKey)
        //将队列名与交换区绑定
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        //自动ack应答
        channel.basicConsume(queueName, true, consumer);
    }
}
