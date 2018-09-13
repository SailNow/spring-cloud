一、服务注册中心
    基于euerka的服务注册中心，提供给各个服务提供注册服务


二、构建euerka-server
1.创建spring-boot模板项目，集成父pom，依赖如下
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
2.在spring-boot入口程序加上@EnableEurekaServer注解
3.配置文件中添加配置
server.port: 8761       --端口号

eureka.instance.hostname:localhost      --hostname
eureka.client.registerWithEureka:false
eureka.client.fetchRegistry:false
eureka.client.serviceUrl.defaultZone:http://localhost:8761/eureka/      --注册中心zone地址




三、改编程序成高可用分布式配置中心(spring cloud config server)
1.pom文件添加依赖
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-server</artifactId>
    </dependency>
