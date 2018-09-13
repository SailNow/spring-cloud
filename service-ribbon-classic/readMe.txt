一、Ribbon简介
    ribbon是一个负载均衡客户端，可以很好的控制htt和tcp的一些行为。Feign默认集成了ribbon。

    ribbon 已经默认实现了这些配置bean：

        IClientConfig ribbonClientConfig: DefaultClientConfigImpl

        IRule ribbonRule: ZoneAvoidanceRule

        IPing ribbonPing: NoOpPing

        ServerList ribbonServerList: ConfigurationBasedServerList

        ServerListFilter ribbonServerListFilter: ZonePreferenceServerListFilter

        ILoadBalancer ribbonLoadBalancer: ZoneAwareLoadBalancer


二、使用
1.创建spring boot模板项目，引入jar
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
    </dependency>

2.配置文件注明注册服务地址以及本项目名称端口
euerka.client.serviceUrl.defaultZone:http://localhost:8761/eureka/
server.port:8765
spring.application.name:service-ribbon

3.在工程的启动类中,通过@EnableDiscoveryClient向服务中心注册；并且向程序的ioc注入一个bean: restTemplate;并通过@LoadBalanced注解表明这个restRemplate开启负载均衡的功能。
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

使用RestTemplate调用服务，其中域名位置是服务注册名称（service-hi）
restTemplate.getForObject("http://service-hi/hi?name="+name,String.class);