1. 把web页面跑起来
遇到的问题
##tomcat启动报错：java.lang.ClassNotFoundException: org.springframework.web.context.ContextLoaderListener
* 添加pom依赖包（无效）
* 升级pom依赖包版本（无效）
* 重新配置tomcat（错误消失，报另一个错误）
##java.lang.IllegalStateException: ApplicationEventMulticaster not initialized
* 注释掉无关代码之后不再报错

##找不到servlet配置文件
项目配置错误，web Resource Directory配置错误。

##找不到layout.vm文件
添加velocity.properties配置文件,修改file.resource.loader.class配置
#velocimacro.library =/macros.vm
input.encoding=UTF-8
output.encoding=UTF-8
#file.resource.loader.path = .

resource.loader = file

file.resource.loader.description = Velocity File Resource Loader
file.resource.loader.class = org.apache.velocity.tools.view.WebappResourceLoader
#file.resource.loader.path = .
file.resource.loader.cache = false
file.resource.loader.modificationCheckInterval = 2