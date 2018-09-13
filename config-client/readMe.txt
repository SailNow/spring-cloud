一、简介
    在分布式系统中，由于服务数量巨多，为了方便服务配置文件统一管理，实时更新，所以需要分布式配置中心组件。在Spring Cloud中，有分布式配置中心组件spring cloud config ，
它支持配置服务放在配置服务的内存中（即本地），也支持放在远程Git仓库中。在spring cloud config 组件中，分两个角色，一是config server，二是config client。

二、构建config client
1.重新创建一个springboot项目，取名为config-client,集成父pom文件，依赖如下
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
    </dependencies>

2.其配置文件bootstrap.properties
spring.application.name=config-client   --注册名称
spring.cloud.config.label=master        --github分支名称
spring.cloud.config.profile=dev         --profile
spring.cloud.config.uri= http://localhost:8888/     --config server
server.port=8881                    --端口号

config-server项目制定github目录下上传属性文件，config-client-dev.properties    [application-name]-[profile].properties

3.使用中可以直接通过@Value("name")的形式获取属性文件中键值对



三、改建高可用分布式配置中心
1.将其注册微到服务注册中心，作为Eureka客户端，需要pom文件加上起步依赖spring-cloud-starter-netflix-eureka-client
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>

2.修改配置文件
注释掉spring.cloud.config.uri，添加如下配置
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/      --注册中心地址
spring.cloud.config.discovery.enabled=true          --是从配置中心读取文件。
spring.cloud.config.discovery.serviceId=config-server        配置中心的servieId，即服务名