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