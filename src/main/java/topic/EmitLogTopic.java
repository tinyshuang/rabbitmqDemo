package topic;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import common.ConnectionTool;

/**
 * 一个类似正则模式的交换区
 */
public class EmitLogTopic {
    private static final String EXCHANGE_NAME = "topic_logs";

    //发送的日志内容
    private static  final String message = "asd";

    //路由规则,分割字符必须是小数点
    private static final String routingKey = "high.rich.homesome";

    public static void main(String[] argv) {
        Connection connection = null;
        Channel channel = null;
        try {

            connection = ConnectionTool.getConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);


            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

        }
        catch  (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (Exception ignore) {}
            }
        }
    }

    private static String getRouting(String[] strings){
        if (strings.length < 1)
            return "anonymous.info";
        return strings[0];
    }

}
