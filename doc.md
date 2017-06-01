[TOC]
##tomcat启动报错：java.lang.ClassNotFoundException: org.springframework.web.context.ContextLoaderListener
* 添加pom依赖包（无效）
* 升级pom依赖包版本（无效）
* 重新配置tomcat（错误消失，报另一个错误）

* 另一个项目出现的同样的问题：应该打war包的，配置成了打jar包
##java.lang.IllegalStateException: ApplicationEventMulticaster not initialized
* 注释掉无关代码之后不再报错

##找不到servlet配置文件
项目配置错误，web Resource Directory配置错误。

##找不到layout.vm文件
添加velocity.properties配置文件

##添加logback.xml后报错：Caused by: org.xml.sax.SAXParseException; lineNumber: 3; columnNumber: 16; Document root element "configuration", must match DOCTYPE root "null".
把logback.xml文件加入了global-config.xml文件，导致在加载的时候按照一定类型加载时文件格式不对；把配置去掉即可。

##设置的切面不起作用
没扫包

##权限控制的preHandle不起效
需要把配置放在dispatch-servlet中进行配置

##获取帐号登陆信息
在登陆的时候保存帐号信息到cookie中，每次在获取页面请求的时候，先从cookie中取帐号信息进行校验，校验通过请求url

##redis使用
1. 安装redis
2. 代码引入redis api包，设置redispool
3. 注意：key不能是null
4. 程序运行前，需要先启动RedisServer
   % cd src
   % ./redis-server

##添加EventData和Profiler
EventDate：以xml格式输出日志信息
Profiler：打印系统调用日志(可打印多级调用信息)

##通过jdk动态代理实现注解处理器
createInstance的时候，报错：Method threw 'java.lang.NullPointerException' exception. Cannot evaluate com.sun.proxy.$Proxy29.toString()
https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Proxy.html
如果数组参数的接口或它的任何元素是null时，报空指针错误

##添加自定义注解Cach，把数据库查询结果缓存到Map中
为获取方法返回结果，通过切面的方式实现注解处理器
对同一个方法使用AfterReturning切面注解，会导致其中一个注解不起作用
使用实体包装Map中的value，保存添加时间，实现缓存的时间设置

##kafka stream
producer读入产生的日志文件(Profile)
consumer保存用户id和浏览行为到数据库 (Properties文件定义相关接口和对应的行为,便于归纳整理)
stream保存用户id,调用接口名称,调用时间(后续可根据时间梳理用户浏览习惯,保存在文件中)
+ Profiler [statics]
|-- elapsed time [execution(RedisService.get(..))]     1.404 milliseconds.
|-- elapsed time [execution(UserManageService.queryUserInfo())]     0.361 milliseconds.
|-- elapsed time [execution(UserInfoService.queryUserInfo())]     0.811 milliseconds.
|-- elapsed time [execution(UserInfoMapper.queryUserInfo())]   574.044 milliseconds.
|-- Total                       [statics]  6525.988 milliseconds.

