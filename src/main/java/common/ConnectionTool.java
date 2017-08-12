package common;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by asus on 2017/8/12.
 */
public class ConnectionTool {
    private  static  ConnectionFactory factory = new ConnectionFactory();

    static {
        factory.setHost(Const.host);
        factory.setUsername(Const.userName);
        factory.setPassword(Const.password);
    }

    /**
     *
     * @return 获取链接
     * @throws IOException
     * @throws TimeoutException
     */
    public  static Connection getConnection() throws IOException, TimeoutException {
        //创建链接
        Connection connection = factory.newConnection();
        return connection;
    }
}
