# 课题研究



### **前言**



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









##### **点赞功能的传统实现**



1)数据库保存实现

2)查看Mysql内存使用情况（注意搜索一些可视化工具）



##### **点赞功能Redis缓存的实现**



0）redis数据类型的了解

【1】http://www.cnblogs.com/qunshu/p/3196972.html

1)redis的数据结构设计

主要是Java 操作Redis   参考 ：http://www.jianshu.com/p/7bf5dc61ca06



考虑用Set数据类型来实现



2)redisTemplate 和 stringRedisTemplate 区别

参考：http://blog.csdn.net/notsaltedfish/article/details/75948281#0-tsina-1-4892-397232819ff9a47a7b7e80a40613cfe1



最终决定选择用：redisTemplate  来实现 ，数据类型是：set



key：mood_id   value: MoodPraiseRel对象



key：xxxxx     value ：Map （Map<String,List>  ）



##### **添加定时器，点赞批量入库(注意分布式情况)**





**点赞功能添加队列抵抗大并发**





##### **并发工具的使用**

1）Jmeter工具的使用









### **Redis分布式锁实现秒杀功能**



1)锁的超时时间如何设置，如何评估这个时间



2）

