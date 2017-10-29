# rabbitmqDemo

安装:

     安装依赖文件：
        yum install gcc glibc-devel make ncurses-devel openssl-devel xmlto

安装erlang

     下载tar包 :- wget http://www.erlang.org/download/otp_src_19.2.tar.gz
      tar -xzvf ###.tar.gz
 
进入解压的文件夹
      
      配置安装路径编译代码：
           ./configure --prefix=/opt/erlang
        执行编译结果：
           make && make install
       完成后进入/opt/erlang/bin查看执行结果 : erl

安装mq

    tar -xvf ###.tar
    
    修改环境变量
    vim /etc/profile
    export PATH=$PATH:/usr/local/software/rabbitmq/sbin
    source /etc/profile


mq官网的自学记录

官网地址: http://www.rabbitmq.com/getstarted.html

API地址 :http://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/

官网代码 : https://github.com/rabbitmq/rabbitmq-tutorials


生产者是不直接跟队列打交道的,而是先通过交换区,让交换区跟队列打交道..

exchange类型区别

    fanout:   广播类型,不在乎队列名字
        针对一条消息多个操作适用..比如用户购买下单,积分,下单扣款等多个功能可以拆开来
      
    Direct:   路由算法是消息会路由到对应的 binding key的队列
        
    topic :   路由算法是模式规则的..可灵活选择队列

        * (star) can substitute for exactly one word.
        # (hash) can substitute for zero or more words.
        
        
Spring-AMQP        
默认情况下CachingConnectionFactory的cache不是一个限制数,而是连接池的一个最小链接数...但是当属性channelCheckoutTimeout值大于0时,channelCacheSize就是一个限制链接数的值,同时它还会监听此时的链接时间,如果超过会抛出AmqpTimeoutException异常
    
    It is important to understand that the cache size is (by default) not a limit, but merely the number of channels that can be cached. With a cache size of, say, 10, any number of channels can actually be in use. If more than 10 channels are being used and they are all returned to the cache, 10 will go in the cache; the remainder will be physically closed.
    
    Starting with version 1.4.2, the CachingConnectionFactory has a property channelCheckoutTimeout. When this property is greater than zero, the channelCacheSize becomes a limit on the number of channels that can be created on a connection. If the limit is reached, calling threads will block until a channel is available or this timeout is reached, in which case a AmqpTimeoutException is thrown.
    
    
connectionLimit限制最大链接数


AMQP的cache格式分为CHANNEL与CONNECTION,rabbitmq默认是基于渠道的,而链接数的模式在某些HA负载均衡会有用..


集群模式下的工厂配置 :

    <bean id="connectionFactory"
          class="org.springframework.amqp.rabbit.connection.SimpleRoutingConnectionFactory">
        <property name="targetConnectionFactories">
            <map>
                <entry key="#{connectionFactory1.virtualHost}" ref="connectionFactory1"/>
                <entry key="#{connectionFactory2.virtualHost}" ref="connectionFactory2"/>
            </map>
        </property>
    </bean>
    
    <rabbit:template id="template" connection-factory="connectionFactory" />


