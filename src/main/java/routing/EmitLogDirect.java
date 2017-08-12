package routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import common.ConnectionTool;

/**
 * 直接路由
 */
public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    //发送的日志内容
    private static  final String message = "asd";

    //发送的消息级别
    private static  final String severity = "error";

    public static void main(String[] argv) throws Exception {

        Connection connection = ConnectionTool.getConnection();
        Channel channel = connection.createChannel();

        //设定交换区类型为直接路由
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);


        channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

        channel.close();
        connection.close();
    }


}
