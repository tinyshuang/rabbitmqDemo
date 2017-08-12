# rabbitmqDemo

mq官网的自学记录

官网地址: http://www.rabbitmq.com/getstarted.html

API地址 :http://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/

官网代码 : https://github.com/rabbitmq/rabbitmq-tutorials


生产者是不直接跟队列打交道的,而是先通过交换区,让交换区跟队列打交道..

exchange类型区别

    fanout:   广播类型,不在乎队列名字
    Direct:   路由算法是消息会路由到对应的 binding key的队列
    topic :   路由算法是模式规则的..

        * (star) can substitute for exactly one word.
        # (hash) can substitute for zero or more words.