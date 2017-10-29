package springAmqp;

/**
 * Created by asus on 2017/10/8.
 */
public class SpringWayLister implements Listen{
    public void listen(String foo) {
        System.out.println("输出 : "+foo);
    }
}
