# 课题研究



### **前言**







##### **课程介绍**



《SpringBoot + Redis + MQ 高并发点赞项目实战》是一套项目实战的项目，通过该项目的学习，学员可以快速掌握Spring Boot使用、Redis缓存的使用、ActiveMQ消息中间件的使用。同时可以了解大型互联网公司如何解决高并发的问题。本套视频提供了完整的高并发的解决方案，让学员掌握高并发的解决方案。



##### **开发环境**

Intellij IDEA + JDK(8) + SpringBoot

##### **课程目标**



帮助学员



##### **课程计划**

更新完成

##### **课程目录**

- 课程介绍


- 一分钟快速搭建Spring Boot
- Spring Boot连接MySQL数据库
- 数据库表设计
- 创建数据库表
- 实体类Model开发
- Spring Boot集成Spring Data JPA
- Repository类开发
- Spring Boot集成Thymeleaf模板引擎
- Controller类Service类开发
- findAll方法实现
- Thymeleaf用户说说数据展示
- 用户点赞功能实现
- Redis缓存安装
- Spring Boot集成Redis缓存
- 设计说说点赞Redis数据结构
- 说说点赞Redis实现
- Spring Boot集成Quartz定时器
- Quartz定时器点赞记录入库
- ActiveMQ介绍与安装
- Spring Boot集成MctiveMQ
- ActiveMQ异步方式实现点赞
- 点赞功能设计方案梳理







开发环境要求：

Intellij IDEA + JDK7(以上) + MySQL







### 高并发点赞功能设计



##### 一分钟快速搭建Spring Boot



创建项目：high-concurrency-praise



##### Spring Boot连接MySQL数据库



创建数据库：high-concurrency-praise



##### 数据库表的设计



1.分析下微博说说内容：具体看**微博内容.png**



要求：每一次点赞,需要记录：

- 谁点的赞;
- 为那篇文章点的赞;
- 点赞时间
- 是否已经取消点赞




##### 创建数据库表



1.数据库表的字段长度限制



##### **实体类Model开发**



1)实体开发



##### Spring Boot集成Spring Data JPA



**Spring Data JPA简介：**

JPA(Java Persistence API)是Sun官方提出的Java持久化规范。所谓规范即只定义标准规则，不提供实现。而JPA的主要实现有Hibernate、EclipseLink、OpenJPA等。JPA是一套规范，不是一套产品。Hibernate是一套产品，如果这些产品实现了JPA规范，那么我们可以叫它们为JPA的实现产品。

Spring DataJPA是Spring Data的一个子项目，它通过提供基于JPA的Respository，极大地减少了JPA作为数据访问方案的代码量。通过Spring Data JPA框架，开发者可以省略实现持久层业务逻辑的工作，唯一要做的，就只是声明持久层的接口，其它都交给 SpringData JPA 来帮你完成。



##### Repository类开发







##### **Controller类Service类开发**





##### Spring Boot集成thymeleaf模板引擎





##### findAll方法实现





##### Thymeleaf微博说说数据展示







##### 用户点赞功能实现



##### Redis缓存安装



- Redis缓存安装（下载地址：<http://redis.io/>   ）   



##### 







\###增加一个值key为name，value为ay

127.0.0.1:6379> set name 'ay'

OK

\###查询name的值

127.0.0.1:6379> get name

"ay"

\###更新name的值为al

127.0.0.1:6379> set name 'al'

OK

\###查询name的值

127.0.0.1:6379> get name

"al"

\###删除name的值

127.0.0.1:6379> del name

(integer) 1

\###查询是否存在name，0代表不存在

127.0.0.1:6379> exists name

(integer) 0

127.0.0.1:6379>

List集合的增删改查：

\###添加key为user_list，value为’ay’,’al’的list集合

127.0.0.1:6379> lpush user_list 'ay' 'al'

(integer) 2

\###查询key为user_list的集合

127.0.0.1:6379> lrange user_list 0 -1

1) "al"

2) "ay"

\###往list尾部添加love元素

127.0.0.1:6379> rpush user_list 'love'

(integer) 3

\###往list头部添加hope元素

127.0.0.1:6379> lpush user_list 'hope'

(integer) 4

\###查询key为user_list的集合

127.0.0.1:6379> lrange user_list 0 -1

1) "hope"

2) "al"

3) "ay"

4) "love"

\###更新index为0的值

127.0.0.1:6379> lset user_list 0 'wish'

OK

\###查询key为user_list的集合

127.0.0.1:6379> lrange user_list 0 -1

1) "wish"

2) "al"

3) "ay"

4) "love"

\###删除index为0的值

127.0.0.1:6379> lrem user_list 0 'wish'

(integer) 1

\###查询key为user_list的集合

127.0.0.1:6379> lrange user_list 0 -1

1) "al"

2) "ay"

3) "love"

127.0.0.1:6379>

Set集合的增删改查：

\###添加key为user_set,value为"ay" "al"  "love"的集合

127.0.0.1:6379> sadd user_set "ay" "al"  "love"

(integer) 3

\###查询key为user_set集合

127.0.0.1:6379> smembers user_set

1) "al"

2) "ay"

3) "love"

\###删除value为love，返回1表示删除成功，0表示失败

127.0.0.1:6379> srem user_set 'love'

(integer) 1

\###查询set集合所有值

127.0.0.1:6379> smembers user_set

1) "al"

2) "ay"

\###添加love元素，set集合是没有顺序的，所以无法判断添加到那个位置

127.0.0.1:6379> sadd user_set 'love'

(integer) 1

\###查询set集合所有值，发现添加到第二个位置

127.0.0.1:6379> smembers user_set

1) "al"

2) "love"

3) "ay"

\###添加love元素，由于set集合已经存在，返回0代表添加不成功，但是不会报错

127.0.0.1:6379> sadd user_set 'love'

(integer) 0

​    Hash集合的增删改查：

\###清除数据库

127.0.0.1:6379> flushdb

OK

\###创建hash，key为user_hset,字段为user1，值为ay

127.0.0.1:6379> hset user_hset "user1"  "ay"

(integer) 1

\###往key为user_hset添加字段为user2，值为al

127.0.0.1:6379> hset user_hset "user2"  "al"

(integer) 1

\###查询user_hset字段长度

127.0.0.1:6379> hlen user_hset

(integer) 2

\###查询user_hset所有字段

127.0.0.1:6379> hkeys user_hset

1) "user1"

2) "user2"

\###查询user_hset所有值

127.0.0.1:6379> hvals user_hset

1) "ay"

2) "al"

\###查询字段user1的值

127.0.0.1:6379> hget user_hset "user1"

"ay"

\###获取key为user_hset所有的字段和值

127.0.0.1:6379> hgetall user_hset

1) "user1"

2) "ay"

3) "user2"

4) "al"

\###更新字段user1的值为new_ay

127.0.0.1:6379> hset user_hset "user1""new_ay"

(integer) 0

\###更新字段user2的值为new_al

127.0.0.1:6379> hset user_hset "user2""new_al"

(integer) 0

\###获取key为user_hset所有的字段和值

127.0.0.1:6379> hgetall user_hset

1) "user1"

2) "new_ay"

3) "user2"

4) "new_al"

\###删除字段user1和值

127.0.0.1:6379> hdel user_hset user1

(integer) 1

\###获取key为user_hset所有的字段和值

127.0.0.1:6379> hgetall user_hset

1) "user2"

2) "new_al"

127.0.0.1:6379>

SortedSet集合的增删改查

\###清除数据库

127.0.0.1:6379> flushdb

OK

\###SortedSet集合添加ay元素，分数为1

127.0.0.1:6379> zadd user_zset 1 "ay"

(integer) 1

\###SortedSet集合添加al元素，分数为2

127.0.0.1:6379> zadd user_zset 2 "al"

(integer) 1

\###SortedSet集合添加love元素，分数为3

127.0.0.1:6379> zadd user_zset 3 "love"

(integer) 1

\###按照分数由小到大查询user_zset集合元素

127.0.0.1:6379> zrange user_zset 0 -1

1) "ay"

2) "al"

3) "love"

\###按照分数由大到小查询user_zset集合元素

127.0.0.1:6379> zrevrange user_zset 0 -1

1) "love"

2) "al"

3) "ay"

\###查询元素ay的分数值

127.0.0.1:6379> zscore user_zset "ay"

"1"

\###查询元素love的分数值

127.0.0.1:6379> zscore user_zset "love"

"3"



##### Spring Boot集成Redis缓存





##### 设计说说点赞Redis数据结构







##### **点赞功能Redis缓存的实现**



0）redis数据类型的了解

【1】http://www.cnblogs.com/qunshu/p/3196972.html

1)redis的数据结构设计

主要是Java 操作Redis   参考 ：http://www.jianshu.com/p/7bf5dc61ca06



考虑用Set数据类型来实现



2)redisTemplate 和 stringRedisTemplate 区别

参考：http://blog.csdn.net/notsaltedfish/article/details/75948281#0-tsina-1-4892-397232819ff9a47a7b7e80a40613cfe1



最终决定选择用：redisTemplate  来实现 ，数据类型是：set





##### SpringBoot集成Quartz定时器





##### Quartz定时器点赞批量入库(注意分布式情况)





##### ActiveMQ介绍与安装









##### Spring Boot集成Active MQ



1.添加依赖：



2.添加配置：

spring.activemq.broker-url=tcp://localhost:61616

spring.activemq.in-memory=true

spring.activemq.pool.enabled=false

spring.activemq.packages.trust-all=true



**点赞功能添加MQ队列抵抗大并发**







##### 点赞功能设计方案梳理



