一、zuul简介
    Zuul的主要功能是路由转发和过滤器。路由功能是微服务的一部分，比如／api/user转发到到user服务，/api/shop转发到到shop服务。zuul默认和Ribbon结合实现了负载均衡的功能。
    zuul有以下功能：
        Authentication
        Insights
        Stress Testing
        Canary Testing
        Dynamic Routing
        Service Migration
        Load Shedding
        Security
        Static Response handling
        Active/Active traffic management

二、使用zuul
1.spring boot  euerka-server模板创建项目，添加maven依赖
    <dependencies>
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
            <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
        </dependency>
    </dependencies>
2.在其入口applicaton类加上注解@EnableZuulProxy，开启zuul的功能
3.配置配置文件
    首先指定服务注册中心的地址为http://localhost:8761/eureka/，服务的端口为8769，服务名为service-zuul；以/api-a/ 开头的请求都转发给service-ribbon服务；以/api-b/开头的请求都转发给service-feign服务
    server.port: 8769  //端口
    spring.application.name: service-zuul   //注册中心注册服务名称

    euerka.client.serviceUrl.defaultZone: http://localhost:8761/eureka/     //注册中心地址

    zuul.routes.api-a.path: /api-a/**           //api-a服务url
    zuul.routes.api-a.serviceId: service-ribbon     //api-a服务名称
    zuul.routes.api-b.path: /api-b/**           //api-b服务url
    zuul.routes.api-b.serviceId: service-feign      //api-b服务名称

三、服务过滤 ZuulFilter
1.集成类ZuulFilter，实现方法，其中run是主要过滤逻辑，需要托管给spring容器
setSendZuulResponse(boolean) 设置拦截结果
如果不拦截的情况下不能使用write，否则报错

filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：

    pre：路由之前
    routing：路由之时
    post： 路由之后
    error：发送错误调用
    filterOrder：过滤的顺序
    shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
    run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。

