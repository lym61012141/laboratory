# 实验项目
#### 拆分为common,redis,rabbitmq,elasticsearch,db(mysql,oracle,mongodb,redis)等项目单独实验
#### 以上实验项目均在springboot2.x版本构建
一. advanced-redis 
- 已增加延迟消息队列实现 **RedisDelayQueue**
- 实现分布式可重入锁 **RedisReentrantLock**

二. common 模块为平时使用的工具类的封装

 - CGLibDynamicProxy
 - JDKDynamicProxy
 - ReflectionUtil
 - CastUtil
 - ClassUtil
 - StreamUtil
 - JsonUtil
 - FileUtil
 - PropsUtil
 - StringUtil
 - TimeUtil
  ...
  
三. RabbitMq 基础项目后期会合并

四. 后期增加Spring 事务相关实验
- 实现脚手架，
 - ORM：spring-data-jpa,
 - DB: Mysql
 - 增加注解 @PrintLog 通过注解形式解决接口调用入参，出参打印入侵代码问题

五. ElasticSearch 只做简单demo

六. 重写了framework
 - IOC 容器支持接口多实现注入
 - 增加了@Primary,@Order 解决接口多个实现类问题
 - 增加@ResponseBody注解
 - 目前存在问题：测试该框架时，IOC 容器初始化时，如果项目域名与框架域名不一致，则IOC容器为空，需要调试IOC 初始化路径问题
 
七. Web项目Authorization 鉴权token方式简单实现redis存储（此处没有加密，仅做简单功能验证），Interceptor实现类获取并鉴权
    _技术栈：redis,jpa_
 ##### 对于Interceptor实现类后续增加处理方案
 - todo 指定拦截：可以增加注解的方式，根据注解拦截需要授权的接口
 - todo 统一处理返回结果：可以根据是否授权在这里直接写入返回结果到response中
 ##### springboot 定时任务使用 ScheduledService
 - 配置AsyncConfig支持多线程异步定时任务执行
 - ScheduledService 需要在类上开启@EnableScheduling
    * 同时每个任务方法上加入@Scheduled定时类型，@Async 开启支持异步
    * 不支持动态修改cron
    * 不支持动态修改任务
##### 更强的com.sydml.authorization.platform.job.DynamicTask类
 - 支持动态增加任务,停止任务，动态修改cron
 - scheduledMap 内存中存放任务，根据指定key停止定时任务
 
##### com.sydml.authorization.platform.job.DynamicScheduled
- 支持动态增加任务,停止任务，动态修改cron
- 反射获取scheduledTasks,进行增加,停止和删除任务

##### 增加动态注入Bean类 com.sydml.authorization.platform.spring.DynamicBeanAware
 - getBeanFactory获取beanFactory
 - autowareSingletonBean 动态注入bean,单利模式
 - autoPrototypeBean 动态注入bean，多例模式
