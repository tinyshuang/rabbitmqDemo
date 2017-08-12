package subscribe;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import common.ConnectionTool;

public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";

    //字符串里面的一个点代表睡眠一秒
    private  static final  String mqMessage = "asd";

    public static void main(String[] argv) throws Exception {
        Connection connection = ConnectionTool.getConnection();
        Channel channel = connection.createChannel();

        //声明一个交换区,并使用交换类型为FANOUT
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //第一个参数表示使用指定的交换区 生产者只能将消息传递给交换区 当交换区为空时使用routingKey
        //FANOUT交换区无需关心消息类型,全员传播所以无需指定routingKey
        channel.basicPublish(EXCHANGE_NAME, "", null, mqMessage.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + mqMessage + "'");

        channel.close();
        connection.close();
    }

}
