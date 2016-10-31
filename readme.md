#遇到的问题(项目初始化)
##tomcat启动报错：java.lang.ClassNotFoundException: org.springframework.web.context.ContextLoaderListener
* 添加pom依赖包（无效）
* 升级pom依赖包版本（无效）
* 重新配置tomcat（错误消失，报另一个错误）
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
