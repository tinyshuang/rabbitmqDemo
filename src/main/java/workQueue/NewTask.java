package workQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import common.ConnectionTool;

public class NewTask {
    private static final String TASK_QUEUE_NAME = "task_queue";

    //字符串里面的一个点代表睡眠一秒
    private  static final  String mqMessage = "qwe.qwe.dfg";

    public static void main(String[] argv) throws Exception {

        Connection connection = ConnectionTool.getConnection();
        Channel channel = connection.createChannel();

        //第二个参数声明队列为持久性队列
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);


        //PERSISTENT_TEXT_PLAIN声明消息为持久化消息
        channel.basicPublish("", TASK_QUEUE_NAME,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                mqMessage.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + mqMessage + "'");

        channel.close();
        connection.close();
    }


}
